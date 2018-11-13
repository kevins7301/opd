package com.iisi.opd.auth.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.auth.dao.RoleDAO;
import com.iisi.opd.auth.po.RolePo;
import com.iisi.opd.auth.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    public static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleDAO roleDAO;

    @Override
    public RolePo add(RolePo po) {
        return this.roleDAO.save(po);
    }

    @Override
    public void delete(RolePo po) {
        this.roleDAO.delete(po);
    }

    @Override
    public void update(RolePo po) {
        this.roleDAO.update(po);
    }

    @Override
    public List<RolePo> findAll() {
        return this.roleDAO.findAll(RolePo.class);
    }

    @Override
    public RolePo findByOid(String oid) {
        RolePo po = this.roleDAO.findById(oid);
        log.debug("RolePo：", po);
        return po;
    }

    @Override
    public RolePo findByRoleName(String name) {
        RolePo po = this.roleDAO.findByRoleName(name);
        log.debug("RolePo：", po);
        return po;
    }

    @Override
    public List<RolePo> findByNameAndStatus(String roleName, Boolean isActive) {
        return this.roleDAO.findByNameAndStatus(roleName, isActive);
    }
}
