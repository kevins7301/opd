package com.iisi.opd.auth.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.iisi.opd.auth.dao.MenuOrderDAO;
import com.iisi.opd.auth.dto.FunctionDto;
import com.iisi.opd.auth.dto.RoleDto;
import com.iisi.opd.auth.dto.UserDto;
import com.iisi.opd.auth.po.MenuOrderPo;

@org.springframework.stereotype.Service
@Transactional
public class MenuOrderServiceImpl implements com.iisi.opd.auth.service.MenuOrderService {
    @org.springframework.beans.factory.annotation.Autowired
    MenuOrderDAO menuOrderDao;

    @Override
    public void doOrder(UserDto dto) {
        List<RoleDto> roles = dto.getRoles();
        for (RoleDto role : roles) {
            doOrder(role);
        }
    }

    @Override
    public void doOrder(RoleDto dto) {
        List<FunctionDto> functions = dto.getFunctions();

        for (FunctionDto function : functions) {
            doOrder(function);
        }

        List<FunctionDto> newFunctions = new ArrayList<FunctionDto>();
        List<MenuOrderPo> orders = this.menuOrderDao.findChildOrderInfo(dto.getOid());
        if (orders.size() > 0) {
            for (Iterator<MenuOrderPo> i$ = orders.iterator(); i$.hasNext();) {
                MenuOrderPo order = i$.next();
                for (FunctionDto function : functions) {
                    if (function.getOid().equals(order.getChildID()))
                        newFunctions.add(function);
                }
            }

            dto.setFunctions(newFunctions);
        }
    }

    @Override
    public void doOrder(FunctionDto dto) {

        List<FunctionDto> newFunctions = new ArrayList<FunctionDto>();
        List<MenuOrderPo> orders = this.menuOrderDao.findChildOrderInfo(dto.getOid());
        if (orders.size() > 0) {
            for (Iterator<MenuOrderPo> i$ = orders.iterator(); i$.hasNext();) {
                MenuOrderPo order = i$.next();
                for (FunctionDto function : dto.getChildFunctions()) {
                    if (function.getOid().equals(order.getChildID()))
                        newFunctions.add(function);
                }
            }

            dto.setChildFunctions(newFunctions);
        }
    }

    @Override
    public void deleteChildsByParentID(String parentID) {
        this.menuOrderDao.deleteChildsByParentID(parentID);
    }

    @Override
    public MenuOrderPo add(MenuOrderPo po) {
        return this.menuOrderDao.save(po);
    }

    @Override
    public List<MenuOrderPo> findChildOrderInfo(String parentID) {
        return this.menuOrderDao.findChildOrderInfo(parentID);
    }
}