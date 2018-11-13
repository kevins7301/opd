/*     */ package com.iisi.opd.auth.service;
/*     */ 
/*     */ import com.iisi.opd.auth.dto.FunctionDto;
/*     */ import com.iisi.opd.auth.dto.RoleDto;
/*     */ import com.iisi.opd.auth.dto.UnitDto;
/*     */ import com.iisi.opd.auth.dto.UserDto;
/*     */ import com.iisi.opd.auth.po.FunctionPo;
/*     */ import com.iisi.opd.auth.po.RolePo;
/*     */ import com.iisi.opd.auth.po.UnitPo;
/*     */ import com.iisi.opd.auth.po.UserPo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ @Component
/*     */ public class DtoPoTransformer
/*     */ {
/*  30 */   public static Logger log = LoggerFactory.getLogger(DtoPoTransformer.class);
/*     */   
/*     */   @Autowired
/*     */   private UnitService unitSvc;
/*     */   
/*     */   public UserDto convertPoToDto(UserPo po)
/*     */   {
/*  37 */     UserDto dto = new UserDto();
/*  38 */     if (po != null) {
/*  39 */       dto.setEmail(po.getEmail());
/*  40 */       dto.setPassword(po.getPassword());
/*  41 */       dto.setRoles(convertRolePoToDto(po.getRoles()));
/*  42 */       dto.setOid(po.getOid());
/*  43 */       dto.setUserName(po.getUserName());
/*  44 */       dto.setLoginId(po.getLoginId());
/*  45 */       dto.setActive(po.isActive());
/*  46 */       dto.setUnit(convertPoToDto(po.getUnit()));
/*  47 */       dto.setActiveRole((RoleDto)dto.getRoles().get(0));
/*  48 */       dto.setPhone(po.getPhone());
/*  49 */       dto.setCreateTime(po.getCreateTime());
/*  50 */       dto.setLastUpdateTime(po.getLastUpdateTime());
/*     */     }
/*  52 */     return dto;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnitDto convertPoToDto(UnitPo po)
/*     */   {
/*  61 */     UnitDto dto = new UnitDto();
/*  62 */     if (this.unitSvc != null) {
/*  63 */       if (po != null) {
/*  64 */         dto.setOtherMonitorUnits(convertUnitPoToDto(po.getOtherMonitorUnits()));
/*  65 */         if (po.getParentUnitID().equals("ROOT")) {
/*  66 */           dto.setRoot(true);
/*  67 */           dto.setParent(null);
/*     */         } else {
/*  69 */           dto.setRoot(false);
/*  70 */           UnitPo parent = this.unitSvc.findByOid(po.getParentUnitID());
/*  71 */           dto.setParent(convertPoToDto(parent));
/*     */         }
/*  73 */         dto.setUnitAlias(po.getUnitAlias());
/*  74 */         dto.setOid(po.getOid());
/*  75 */         dto.setUnitName(po.getUnitName());
/*  76 */         dto.setUnitPathName(po.getUnitPathName());
/*     */       }
/*     */     } else {
/*  79 */       log.error("取不到UnitService");
/*     */     }
/*  81 */     return dto;
/*     */   }
/*     */   
/*     */   public RoleDto convertPoToDto(RolePo po) {
/*  85 */     RoleDto dto = new RoleDto();
/*  86 */     if (po != null) {
/*  87 */       dto.setFunctions(convertFunctionPoToDto(po.getFunctions()));
/*  88 */       dto.setOid(po.getOid());
/*  89 */       dto.setRoleName(po.getRoleName());
/*  90 */       dto.setRoleLevel(po.getRoleLevel());
/*  91 */       dto.setDescription(po.getDescription());
/*  92 */       dto.setLastEditTime(po.getLastEditTime());
/*  93 */       dto.setLastEditUserName(po.getLastEditUserName());
/*  94 */       dto.setActive(po.isActive());
/*     */     }
/*  96 */     return dto;
/*     */   }
/*     */   
/*     */   private FunctionDto convertPoToDto(FunctionPo po, Map<String, FunctionDto> map) {
/* 100 */     if (map == null)
/* 101 */       map = new HashMap();
/* 102 */     FunctionDto dto = new FunctionDto();
/* 103 */     if ((po != null) && 
/* 104 */       ((dto = (FunctionDto)map.get(po.getOid())) == null)) {
/* 105 */       dto = new FunctionDto();
/* 106 */       dto.setAccessPath(po.getAccessPath());
/* 107 */       dto.setOid(po.getOid());
/* 108 */       dto.setFunctionName(po.getFunctionName());
/* 109 */       dto.setMethodPath(po.getMethodPath());
/* 110 */       dto.setGroup(po.isGroup());
/* 111 */       map.put(dto.getOid(), dto);
/* 112 */       dto.setChildFunctions(convertFunctionPoToDto(po.getChildFunctions(), map));
/* 113 */       dto.setParentFunctions(convertFunctionPoToDto(po.getParentFunctions(), map));
/*     */     }
/*     */     
/* 116 */     return dto;
/*     */   }
/*     */   
/* 119 */   public FunctionDto convertPoToDto(FunctionPo po) { return convertPoToDto(po, null); }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<UserDto> convertUserPoToDto(List<UserPo> pos)
/*     */   {
/* 125 */     List<UserDto> back = new ArrayList();
/* 126 */     for (UserPo po : pos) {
/* 127 */       back.add(convertPoToDto(po));
/*     */     }
/* 129 */     return back;
/*     */   }
/*     */   
/*     */   public List<UnitDto> convertUnitPoToDto(List<UnitPo> pos) {
/* 133 */     List<UnitDto> back = new ArrayList();
/* 134 */     for (UnitPo po : pos) {
/* 135 */       back.add(convertPoToDto(po));
/*     */     }
/* 137 */     return back;
/*     */   }
/*     */   
/*     */   public List<RoleDto> convertRolePoToDto(List<RolePo> pos) {
/* 141 */     List<RoleDto> back = new ArrayList();
/* 142 */     for (RolePo po : pos) {
/* 143 */       back.add(convertPoToDto(po));
/*     */     }
/* 145 */     return back;
/*     */   }
/*     */   
/*     */   private List<FunctionDto> convertFunctionPoToDto(List<FunctionPo> pos, Map<String, FunctionDto> map) {
/* 149 */     if (map == null)
/* 150 */       map = new HashMap();
/* 151 */     List<FunctionDto> back = new ArrayList();
/* 152 */     FunctionDto dto = new FunctionDto();
/* 153 */     for (FunctionPo po : pos) {
/* 154 */       if ((dto = (FunctionDto)map.get(po.getOid())) == null)
/* 155 */         dto = convertPoToDto(po, map);
/* 156 */       back.add(dto);
/*     */     }
/* 158 */     return back;
/*     */   }
/*     */   
/*     */   public List<FunctionDto> convertFunctionPoToDto(List<FunctionPo> pos) {
/* 162 */     return convertFunctionPoToDto(pos, null);
/*     */   }
/*     */   
/*     */ 
/*     */   public UserPo convertDtoToPo(UserDto dto)
/*     */   {
/* 168 */     UserPo po = new UserPo();
/* 169 */     if (dto != null) {
/* 170 */       po.setEmail(dto.getEmail());
/* 171 */       po.setPassword(dto.getPassword());
/* 172 */       po.setRoles(convertRoleDtoToPo(dto.getRoles()));
/* 173 */       po.setOid(dto.getOid());
/* 174 */       po.setUserName(dto.getUserName());
/* 175 */       po.setUnit(convertDtoToPo(dto.getUnit()));
/* 176 */       po.setActive(dto.isActive());
/* 177 */       po.setLoginId(dto.getLoginId());
/* 178 */       po.setPhone(dto.getPhone());
/* 179 */       po.setCreateTime(dto.getCreateTime());
/* 180 */       po.setLastUpdateTime(dto.getLastUpdateTime());
/*     */     }
/* 182 */     return po;
/*     */   }
/*     */   
/*     */   public UnitPo convertDtoToPo(UnitDto dto) {
/* 186 */     UnitPo po = new UnitPo();
/* 187 */     if (dto != null) {
/* 188 */       po.setOtherMonitorUnits(convertUnitDtoToPo(dto.getOtherMonitorUnits()));
/* 189 */       if (dto.isRoot()) {
/* 190 */         po.setParentUnitID("ROOT");
/*     */       } else {
/* 192 */         po.setParentUnitID(dto.getParent().getOid());
/*     */       }
/* 194 */       po.setUnitAlias(dto.getUnitAlias());
/* 195 */       po.setOid(dto.getOid());
/* 196 */       po.setUnitName(dto.getUnitName());
/* 197 */       po.setUnitPathName(dto.getUnitPathName());
/*     */     }
/* 199 */     return po;
/*     */   }
/*     */   
/*     */   public RolePo convertDtoToPo(RoleDto dto) {
/* 203 */     RolePo po = new RolePo();
/* 204 */     if (dto != null) {
/* 205 */       po.setFunctions(convertFunctionDtoToPo(dto.getFunctions()));
/* 206 */       po.setOid(dto.getOid());
/* 207 */       po.setRoleName(dto.getRoleName());
/* 208 */       po.setRoleLevel(dto.getRoleLevel());
/* 209 */       po.setDescription(dto.getDescription());
/* 210 */       po.setLastEditTime(dto.getLastEditTime());
/* 211 */       po.setLastEditUserName(dto.getLastEditUserName());
/*     */     }
/* 213 */     return po;
/*     */   }
/*     */   
/*     */   public FunctionPo convertDtoToPo(FunctionDto dto) {
/* 217 */     FunctionPo po = new FunctionPo();
/* 218 */     if (dto != null) {
/* 219 */       po.setAccessPath(dto.getAccessPath());
/* 220 */       po.setOid(dto.getOid());
/* 221 */       po.setFunctionName(dto.getFunctionName());
/* 222 */       po.setMethodPath(dto.getMethodPath());
/*     */     }
/* 224 */     return po;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<UserPo> convertUserDtoToPo(List<UserDto> dtos)
/*     */   {
/* 230 */     List<UserPo> back = new ArrayList();
/* 231 */     for (UserDto dto : dtos) {
/* 232 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 234 */     return back;
/*     */   }
/*     */   
/*     */   public List<UnitPo> convertUnitDtoToPo(List<UnitDto> dtos) {
/* 238 */     List<UnitPo> back = new ArrayList();
/* 239 */     for (UnitDto dto : dtos) {
/* 240 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 242 */     return back;
/*     */   }
/*     */   
/*     */   public List<RolePo> convertRoleDtoToPo(List<RoleDto> dtos) {
/* 246 */     List<RolePo> back = new ArrayList();
/* 247 */     for (RoleDto dto : dtos) {
/* 248 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 250 */     return back;
/*     */   }
/*     */   
/*     */   public List<FunctionPo> convertFunctionDtoToPo(List<FunctionDto> dtos) {
/* 254 */     List<FunctionPo> back = new ArrayList();
/* 255 */     for (FunctionDto dto : dtos) {
/* 256 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 258 */     return back;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\service\DtoPoTransformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */