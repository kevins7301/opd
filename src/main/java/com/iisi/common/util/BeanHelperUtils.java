package com.iisi.common.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.iisi.opd.exception.OpdException;
import com.iisi.opd.exception.msg.ErrorCodeEnum;
import com.rits.cloning.Cloner;

import net.sf.cglib.beans.BeanCopier;

public class BeanHelperUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanHelperUtils.class);

    private static Map<String, BeanCopier> beanCopierMap = new HashMap();

    public static Object copyProperties(Object sourceClass, Object targetClass) {
        Object dest = targetClass;
        try {
            if (null == targetClass) {
                Assert.isNull(dest);
                return dest;
            }

            Assert.notNull(dest);

            String beanKey = sourceClass.toString() + targetClass.toString();
            BeanCopier copier = null;

            if (!beanCopierMap.containsKey(beanKey)) {
                copier = BeanCopier.create(sourceClass.getClass(), targetClass.getClass(), false);
                beanCopierMap.put(beanKey, copier);
            } else {
                copier = (BeanCopier) beanCopierMap.get(beanKey);
            }

            copier.copy(sourceClass, targetClass, null);

            getFieldNamesAndValues(sourceClass, dest);
        } catch (Exception ex) {
            logger.error("BeanHelper.copyProperties BeansException error{} ", ex.getCause());
            throw new OpdException(ErrorCodeEnum.ERR_1000500_EXCEPTION);
        }
        return dest;
    }

    private static void getFieldNamesAndValues(Object srcObj, Object tarObj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap();
        Field[] fields = srcObj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            fields[i].setAccessible(true);
            Object value = fields[i].get(srcObj);

            if (fields[i].getType().isAssignableFrom(Boolean.class)) {
                map.put(name, value);
            }
        }

        for (Map.Entry<String, Object> data : map.entrySet()) {
            Field field = ReflectionUtils.findField(tarObj.getClass(), (String) data.getKey());
            if (ObjectUtils.equals(field.getType(), Boolean.class)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, tarObj, Boolean.valueOf(Boolean.parseBoolean(data.getValue().toString())));
            }
        }
    }

    public static <T> T copyClassProperties(Object source, Class<T> targetClass) {
        try {
            Assert.notNull(source);
            T dest = targetClass.newInstance();
            copyProperties(source, dest);
            return dest;
        } catch (Exception ex) {
            logger.error("BeanHelper.copyProperties BeansException error{} ", ex);
            throw new OpdException(ErrorCodeEnum.ERR_1000500_EXCEPTION);
        }
    }

    public static final <FROM, T> List<Object> convertBeanList(List<FROM> entityList, Class<T> targetClass) {
        List<Object> resultList = new ArrayList();
        for (FROM srcObj : entityList) {
            resultList.add(copyProperties(srcObj, targetClass));
        }

        if (resultList.size() > 0) {
            logger.debug("BeanHelper.convertBeanList from {} to {}", entityList.get(0).getClass().getName(),
                    targetClass.getName());
        }

        return resultList;
    }

    public static <T> T clone(T orig) {
        Assert.notNull(orig);
        if ((orig instanceof Serializable)) {
            return (T) SerializationUtils.clone((Serializable) orig);
        }
        try {
            Cloner cloner = new Cloner();
            return cloner.deepClone(orig);
        } catch (Exception e) {
            throw new OpdException(ErrorCodeEnum.ERR_1000500_EXCEPTION);
        }
    }

    public static Object deepCopyProperties(final Object sourceClass, final Object targetClass) throws IllegalArgumentException {
        if (sourceClass == null) {
            throw new OpdException(ErrorCodeEnum.ERR_1000100_EXCEPTION);
        }
        if (targetClass == null) {
            throw new OpdException(ErrorCodeEnum.ERR_1000100_EXCEPTION);
        }
        if (!targetClass.getClass().isAssignableFrom(targetClass.getClass())) {
            throw new OpdException(ErrorCodeEnum.ERR_1000500_EXCEPTION, "Destination class [" + targetClass.getClass().getName()
                    + "] must be same or subclass as source class [" + sourceClass.getClass().getName() + "]");
        }

        ReflectionUtils.doWithFields(sourceClass.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                Object srcValue = field.get(sourceClass);
                field.set(targetClass, srcValue);
            }
        }, new ReflectionUtils.FieldFilter() {
            @Override
            public boolean matches(Field field) {
                int modifiers = field.getModifiers();
                return !Modifier.isStatic(modifiers);
            }
        });

        return targetClass;

    }

    public static String toString(Object obj) {
        Assert.notNull(obj);
        String reflectionToString = ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE, true);

        return reflectionToString;
    }

    public static Map<String, Object> getOriginalFieldValueIfDifferent(Object original,
            Object modified) throws SecurityException, IllegalArgumentException, IllegalAccessException {
        Field[] baseFields = original.getClass().getDeclaredFields();
        Map<String, Object> back = new HashMap();
        for (Field field : baseFields) {
            field.setAccessible(true);
            Object originalFieldValue = field.get(original);
            Field modifiedField = null;
            try {
                modifiedField = modified.getClass().getDeclaredField(field.getName());
                modifiedField.setAccessible(true);
                Object modifiedFieldValue = modifiedField.get(modified);
                if (originalFieldValue == null) {
                    if (modifiedFieldValue != null) {
                        back.put(field.getName(), originalFieldValue);
                    }
                } else if (!originalFieldValue.equals(modifiedFieldValue)) {
                    back.put(field.getName(), originalFieldValue);
                }
            } catch (NoSuchFieldException e) {
                logger.info(field.getName() + "並不在 modified物件中");
            }
        }
        return back;
    }
}