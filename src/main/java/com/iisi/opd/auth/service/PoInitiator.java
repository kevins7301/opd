package com.iisi.opd.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.auth.dao.FunctionDAO;
import com.iisi.opd.auth.dao.RoleDAO;
import com.iisi.opd.auth.dao.UnitDAO;
import com.iisi.opd.auth.dao.UserDAO;
import com.iisi.opd.auth.po.FunctionPo;
import com.iisi.opd.auth.po.RolePo;
import com.iisi.opd.auth.po.UnitPo;
import com.iisi.opd.auth.po.UserPo;

@Transactional
@Component
public class PoInitiator {
    public static final Logger log = LoggerFactory.getLogger(PoInitiator.class);

    @Autowired
    private FunctionDAO functionDao;
    @Autowired
    private UnitDAO unitDao;
    @Autowired
    private RoleDAO roleDao;
    @Autowired
    private UserDAO userDao;
    private boolean useFunctionGroup = false;

    public boolean isUseFunctionGroup() {
        return this.useFunctionGroup;
    }

    public void setUseFunctionGroup(boolean useFunctionGroup) {
        this.useFunctionGroup = useFunctionGroup;
    }

    public boolean doCleanTable() {
        List<UserPo> users = this.userDao.findAll(UserPo.class);
        log.debug("共有" + users.size() + "個使用者PO");
        for (int i = 0; i < users.size(); i++) {
            this.userDao.delete(users.get(i));
        }

        List<UnitPo> units = this.unitDao.findAll(UnitPo.class);
        log.debug("共有" + units.size() + "個單位PO");
        for (int i = 0; i < units.size(); i++) {
            this.unitDao.delete(units.get(i));
        }

        List<RolePo> roles = this.roleDao.findAll(RolePo.class);
        log.debug("共有" + roles.size() + "個角色PO");
        for (int i = 0; i < roles.size(); i++) {
            this.roleDao.delete(roles.get(i));
        }

        List<FunctionPo> functions = this.functionDao.findAll(FunctionPo.class);
        log.debug("共有" + functions.size() + "個功能PO");
        for (int i = 0; i < functions.size(); i++) {
            this.functionDao.delete(functions.get(i));
        }

        int size = this.functionDao.findAll(FunctionPo.class).size() + this.unitDao.findAll(UnitPo.class).size()
                + this.roleDao.findAll(RolePo.class).size() + this.userDao.findAll(UserPo.class).size();

        log.debug("清除資料表執行成功,剩下" + size + "個PO");
        return true;
    }

    public boolean doInitialization() {
        log.debug("PoInitiator(" + this.useFunctionGroup + ") called.");
        int size = this.functionDao.findAll(FunctionPo.class).size() + this.unitDao.findAll(UnitPo.class).size()
                + this.roleDao.findAll(RolePo.class).size() + this.userDao.findAll(UserPo.class).size();

        if (size > 0) {
            log.error("資料庫不為空，禁止執行初始化");
        } else {
            log.debug("資料庫全空，開始初始化");
            if (this.useFunctionGroup) {
                if ((initFunction()) && (initFunctionGroup()) && (initRole(this.useFunctionGroup)) && (initUnit())
                        && (initUser())) {
                    size = this.functionDao.findAll(FunctionPo.class).size() + this.unitDao.findAll(UnitPo.class).size()
                            + this.roleDao.findAll(RolePo.class).size() + this.userDao.findAll(UserPo.class).size();

                    log.debug("初始化執行成功,共" + size + "個PO完成初始化");
                    return true;

                }

            } else if ((initFunction()) && (initRole(this.useFunctionGroup)) && (initUnit()) && (initUser())) {
                size = this.functionDao.findAll(FunctionPo.class).size() + this.unitDao.findAll(UnitPo.class).size()
                        + this.roleDao.findAll(RolePo.class).size() + this.userDao.findAll(UserPo.class).size();

                log.debug("初始化執行成功,共" + size + "個PO完成初始化");
                return true;
            }
        }

        return false;
    }

    public boolean initFunction() {
        if (this.functionDao.findAll(FunctionPo.class).size() > 0) {
            log.error("功能po的資料表不為空，不允許執行初始化");
            return false;
        }
        log.debug("功能po的資料表為空，開始執行初始化");
        FunctionPo functionPo = null;
        functionPo = new FunctionPo();
        functionPo.setAccessPath("/addFunctionAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("功能新增功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        functionPo = this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/deleteFunctionAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("功能刪除功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/editFunctionAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("功能修改功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/listFunctionAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("功能查詢功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/addUnitAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("單位新增功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/deleteUnitAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("單位刪除功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/editUnitAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("單位修改功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/listUnitAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("單位查詢功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/addRoleAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("角色新增功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/deleteRoleAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("角色刪除功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/editRoleAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("角色修改功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/listRoleAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("角色查詢功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/addUserAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("使用者新增功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/deleteUserAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("使用者刪除功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/editUserAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("使用者修改功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        functionPo = new FunctionPo();
        functionPo.setAccessPath("/listUserAction.do");
        functionPo.setChildFunctions(null);
        functionPo.setFunctionName("使用者查詢功能");
        functionPo.setGroup(false);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        log.info("完成功能PO的初始化,共有" + this.functionDao.findAll(FunctionPo.class).size() + "個物件存在資料表中");
        return true;
    }

    public boolean initFunctionGroup() {
        if (this.functionDao.findAllFunctionGroups().size() > 0) {
            log.error("功能群組已存在，不允許執行初始化");
            return false;
        }
        log.debug("資料表中無功能群組，開始執行初始化");
        FunctionPo functionPo = null;
        List<FunctionPo> flist = null;

        flist = new ArrayList<FunctionPo>();
        flist.add(this.functionDao.findByFunctionName("功能新增功能"));
        flist.add(this.functionDao.findByFunctionName("功能刪除功能"));
        flist.add(this.functionDao.findByFunctionName("功能修改功能"));
        flist.add(this.functionDao.findByFunctionName("功能查詢功能"));
        functionPo = new FunctionPo();
        functionPo.setAccessPath("");
        functionPo.setChildFunctions(flist);
        functionPo.setFunctionName("功能功能群組");
        functionPo.setGroup(true);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        flist = new ArrayList<FunctionPo>();
        flist.add(this.functionDao.findByFunctionName("單位新增功能"));
        flist.add(this.functionDao.findByFunctionName("單位刪除功能"));
        flist.add(this.functionDao.findByFunctionName("單位修改功能"));
        flist.add(this.functionDao.findByFunctionName("單位查詢功能"));
        functionPo = new FunctionPo();
        functionPo.setAccessPath("");
        functionPo.setChildFunctions(flist);
        functionPo.setFunctionName("單位功能群組");
        functionPo.setGroup(true);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        flist = new ArrayList<FunctionPo>();
        flist.add(this.functionDao.findByFunctionName("角色新增功能"));
        flist.add(this.functionDao.findByFunctionName("角色刪除功能"));
        flist.add(this.functionDao.findByFunctionName("角色修改功能"));
        flist.add(this.functionDao.findByFunctionName("角色查詢功能"));
        functionPo = new FunctionPo();
        functionPo.setAccessPath("");
        functionPo.setChildFunctions(flist);
        functionPo.setFunctionName("角色功能群組");
        functionPo.setGroup(true);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        flist = new ArrayList<FunctionPo>();
        flist.add(this.functionDao.findByFunctionName("使用者新增功能"));
        flist.add(this.functionDao.findByFunctionName("使用者刪除功能"));
        flist.add(this.functionDao.findByFunctionName("使用者修改功能"));
        flist.add(this.functionDao.findByFunctionName("使用者查詢功能"));
        functionPo = new FunctionPo();
        functionPo.setAccessPath("");
        functionPo.setChildFunctions(flist);
        functionPo.setFunctionName("使用者功能群組");
        functionPo.setGroup(true);
        functionPo.setMethodPath("");
        functionPo.setParentFunctions(null);
        functionPo.setRoles(null);
        this.functionDao.save(functionPo);

        log.info("完成功能群組的初始化，共有" + this.functionDao.findAllFunctionGroups().size() + "個功能群組存在資料表中");
        return true;
    }

    public boolean initUnit() {
        if (this.unitDao.findAll(UnitPo.class).size() > 0) {
            log.error("單位po的資料表不為空，不允許執行初始化");
            return false;
        }
        log.debug("單位po的資料表為空，開始執行初始化");
        UnitPo unitPo = new UnitPo();
        unitPo.setMonitoredUnits(null);
        unitPo.setOtherMonitorUnits(null);
        unitPo.setParentUnitID("ROOT");
        unitPo.setUnitAlias("DEFAULT_UNIT");
        unitPo.setUnitName("預設單位");
        unitPo.setUnitPathName("");
        this.unitDao.save(unitPo);

        log.info("完成單位PO的初始化");
        return true;
    }

    public boolean initRole(boolean useFunctionGroup) {
        if (this.roleDao.findAll(RolePo.class).size() > 0) {
            log.error("角色po的資料表不為空，不允許執行初始化");
            return false;
        }
        log.debug("角色po的資料表為空，開始執行初始化");
        List<FunctionPo> flist = new ArrayList<FunctionPo>();
        if (useFunctionGroup) {
            flist.add(this.functionDao.findByFunctionName("功能功能群組"));
            flist.add(this.functionDao.findByFunctionName("單位功能群組"));
            flist.add(this.functionDao.findByFunctionName("角色功能群組"));
            flist.add(this.functionDao.findByFunctionName("使用者功能群組"));
        } else {
            flist.add(this.functionDao.findByFunctionName("功能新增功能"));
            flist.add(this.functionDao.findByFunctionName("功能刪除功能"));
            flist.add(this.functionDao.findByFunctionName("功能修改功能"));
            flist.add(this.functionDao.findByFunctionName("功能查詢功能"));
            flist.add(this.functionDao.findByFunctionName("單位新增功能"));
            flist.add(this.functionDao.findByFunctionName("單位刪除功能"));
            flist.add(this.functionDao.findByFunctionName("單位修改功能"));
            flist.add(this.functionDao.findByFunctionName("單位查詢功能"));
            flist.add(this.functionDao.findByFunctionName("角色新增功能"));
            flist.add(this.functionDao.findByFunctionName("角色刪除功能"));
            flist.add(this.functionDao.findByFunctionName("角色修改功能"));
            flist.add(this.functionDao.findByFunctionName("角色查詢功能"));
            flist.add(this.functionDao.findByFunctionName("使用者新增功能"));
            flist.add(this.functionDao.findByFunctionName("使用者刪除功能"));
            flist.add(this.functionDao.findByFunctionName("使用者修改功能"));
            flist.add(this.functionDao.findByFunctionName("使用者查詢功能"));
        }

        RolePo rolePo = new RolePo();
        rolePo.setFunctions(flist);
        rolePo.setRoleName("系統管理者角色");
        rolePo.setUsers(null);
        this.roleDao.save(rolePo);

        log.info("完成角色PO的初始化");
        return true;
    }

    public boolean initUser() {
        if (this.userDao.findAll(UserPo.class).size() > 0) {
            log.error("使用者po的資料表不為空，不允許執行初始化");
            return false;
        }
        log.debug("使用者po的資料表為空，開始執行初始化");
        UserPo userPo = new UserPo();
        log.debug("1");
        userPo.setEmail("admin@opd.iisigroup.com");
        log.debug("2");
        userPo.setPassword("admin");
        log.debug("3");
        userPo.setRoles(this.roleDao.findAll(RolePo.class));
        log.debug("4");
        userPo.setUnit(this.unitDao.findByUnitName("預設單位"));
        log.debug("5");
        userPo.setUserName("admin");
        log.debug("6");
        this.userDao.save(userPo);

        log.info("完成使用者PO的初始化");
        return true;
    }

    public static void main(String[] args) {
    }
}
