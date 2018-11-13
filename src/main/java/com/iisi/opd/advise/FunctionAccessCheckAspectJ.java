package com.iisi.opd.advise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iisi.opd.auth.AuthBeanUtils;
import com.iisi.opd.auth.dto.FunctionDto;
import com.iisi.opd.auth.dto.UserDto;

@Aspect
public class FunctionAccessCheckAspectJ {
    public static Logger log = LoggerFactory.getLogger(FunctionAccessCheckAspectJ.class);

    public static String[] skippedURIs = { "/loginAction.do", "/logoutAction.do", "/demoAction.do" };

    public static String[] skippedMethodSignatures = { "xxx.LoginController.login()" };

    @Around("execution(* com.iisi.opd.*.controller.*.*(..)) ")
    public Object doAccessibilityCheckAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if ((joinPoint.getSignature() instanceof MethodSignature)) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            Method method = methodSignature.getMethod();
            log.info("Transferred signature:" + method.getName());

            Annotation[] annotations = method.getAnnotations();
            String uri = null;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getCanonicalName()
                        .equals("org.springframework.web.bind.annotation.RequestMapping")) {
                    RequestMapping rm = (RequestMapping) annotation;
                    String[] params = rm.params();
                    for (String param : params) {
                        log.info(param);
                    }
                    String[] values = rm.value();
                    for (String value : values) {
                        log.info(value);
                        if (value.indexOf("Action.do") > 0) {
                            uri = value;
                        }
                    }
                }
            }
            Object[] args = joinPoint.getArgs();
            log.info("取得joinPoint的參數們");
            HttpSession session = null;
            ModelMap model = null;
            for (Object arg : args) {
                log.info(arg.getClass().getCanonicalName());
                if (arg.getClass().getCanonicalName().equals("org.apache.catalina.connector.RequestFacade")) {
                    HttpServletRequest request = (HttpServletRequest) arg;
                    session = request.getSession();
                } else if (arg.getClass().getCanonicalName()
                        .equals("org.springframework.validation.support.BindingAwareModelMap")) {
                    model = (ModelMap) arg;
                }
            }
            if (session != null) {
                if (session.getAttribute("userDto") == null) {
                    log.info("抓不到使用者資訊");
                    if (isInSkippedURIs(uri)) {
                        return joinPoint.proceed();
                    }
                    model.put("errorMsg", "抓不到使用者資訊");
                    return "showErrorMsg";
                }

                UserDto userDto = (UserDto) session.getAttribute("userDto");
                log.info("使用者是" + userDto.getUserName());
                if (checkByUri(userDto, uri)) {
                    return joinPoint.proceed();
                }
                model.put("errorMsg", "使用者無存取權限");
                return "showErrorMsg";
            }
        }

        return null;
    }

    private boolean checkByUri(UserDto user, String uri) {
        boolean back = false;

        if (isInSkippedURIs(uri)) {
            back = true;
        } else {
            for (FunctionDto function : user.getActiveRole().getFunctions()) {
                if (checkByUri(function, uri)) {
                    back = true;
                }
            }
        }

        return back;
    }

    private boolean checkByUri(FunctionDto function, String uri) {
        boolean back = false;

        if (isInSkippedURIs(uri)) {
            back = true;
        } else if (function.isGroup()) {
            for (FunctionDto fDto : function.getChildFunctions()) {
                if (checkByUri(fDto, uri)) {
                    back = true;
                }

            }
        } else if (function.getAccessPath().equals(uri)) {
            back = true;
        }

        return back;
    }

    private boolean isInSkippedURIs(String uri) {
        boolean back = false;
        for (String skippedURI : skippedURIs) {
            if (skippedURI.equals(uri)) {
                back = true;
            }
        }
        return back;
    }

    private boolean checkByMethodSignature(UserDto user, Method method) {
        boolean back = false;
        if (isInSkippedMethodSignatures(method)) {
            back = true;
        } else {
            for (FunctionDto function : user.getActiveRole().getFunctions()) {
                if (checkByMethodSignature(function, method)) {
                    back = true;
                }
            }
        }
        return back;
    }

    private boolean checkByMethodSignature(FunctionDto function, Method method) {
        boolean back = false;
        if (isInSkippedMethodSignatures(method)) {
            back = true;
        } else {
            String methodSignature = AuthBeanUtils.getMethodSignature(method);
            if (function.isGroup()) {
                for (FunctionDto fDto : function.getChildFunctions()) {
                    if (checkByMethodSignature(fDto, method)) {
                        back = true;
                    }

                }
            } else if (function.getMethodPath().equals(methodSignature)) {
                back = true;
            }
        }

        return back;
    }

    private boolean isInSkippedMethodSignatures(Method method) {
        boolean back = false;
        String methodSignature = AuthBeanUtils.getMethodSignature(method);
        for (String skippedMS : skippedMethodSignatures) {
            if (skippedMS.equals(methodSignature)) {
                back = true;
            }
        }
        return back;
    }
}
