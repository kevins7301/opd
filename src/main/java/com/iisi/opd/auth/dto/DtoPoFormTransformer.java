/*     */ package com.iisi.opd.auth.dto;
/*     */ 
/*     */ import com.iisi.opd.auth.form.FunctionForm;
/*     */ import com.iisi.opd.auth.form.RoleForm;
/*     */ import com.iisi.opd.auth.form.UnitForm;
/*     */ import com.iisi.opd.auth.form.UserForm;
/*     */ import com.iisi.opd.auth.po.FunctionPo;
/*     */ import com.iisi.opd.auth.po.RolePo;
/*     */ import com.iisi.opd.auth.po.UnitPo;
/*     */ import com.iisi.opd.auth.po.UserPo;
/*     */ import com.iisi.opd.auth.service.FunctionService;
/*     */ import com.iisi.opd.auth.service.RoleService;
/*     */ import com.iisi.opd.auth.service.UnitService;
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
/*     */ @Service
/*     */ @Transactional
/*     */ @Component
/*     */ public class DtoPoFormTransformer
/*     */ {
/*  31 */   public static Logger log = LoggerFactory.getLogger(DtoPoFormTransformer.class);
/*     */   
/*     */   @Autowired
/*     */   private UnitService unitSvc;
/*     */   @Autowired
/*     */   private RoleService roleSvc;
/*     */   @Autowired
/*     */   private FunctionService funSvc;
/*     */   
/*     */   public UserDto convertPoToDto(UserPo po)
/*     */   {
/*  42 */     UserDto dto = new UserDto();
/*  43 */     if (po != null) {
/*  44 */       dto.setEmail(po.getEmail());
/*  45 */       dto.setPassword(po.getPassword());
/*  46 */       dto.setRoles(convertRolePoToDto(po.getRoles()));
/*  47 */       dto.setOid(po.getOid());
/*  48 */       dto.setUserName(po.getUserName());
/*  49 */       dto.setLoginId(po.getLoginId());
/*  50 */       dto.setActive(po.isActive());
/*  51 */       dto.setUnit(convertPoToDto(po.getUnit()));
/*  52 */       dto.setActiveRole((RoleDto)dto.getRoles().get(0));
/*  53 */       dto.setPhone(po.getPhone());
/*  54 */       dto.setCreateTime(po.getCreateTime());
/*  55 */       dto.setLastUpdateTime(po.getLastUpdateTime());
/*     */     }
/*  57 */     return dto;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnitDto convertPoToDto(UnitPo po)
/*     */   {
/*  66 */     UnitDto dto = new UnitDto();
/*  67 */     if (this.unitSvc != null) {
/*  68 */       if (po != null) {
/*  69 */         dto.setOtherMonitorUnits(convertUnitPoToDto(po.getOtherMonitorUnits()));
/*  70 */         if (po.getParentUnitID().equals("ROOT")) {
/*  71 */           dto.setRoot(true);
/*  72 */           dto.setParent(null);
/*     */         } else {
/*  74 */           dto.setRoot(false);
/*  75 */           UnitPo parent = this.unitSvc.findByOid(po.getParentUnitID());
/*  76 */           dto.setParent(convertPoToDto(parent));
/*     */         }
/*  78 */         dto.setUnitAlias(po.getUnitAlias());
/*  79 */         dto.setOid(po.getOid());
/*  80 */         dto.setUnitName(po.getUnitName());
/*  81 */         dto.setUnitPathName(po.getUnitPathName());
/*     */       }
/*     */     } else {
/*  84 */       log.error("取不到UnitService");
/*     */     }
/*  86 */     return dto;
/*     */   }
/*     */   
/*     */   public RoleDto convertPoToDto(RolePo po) {
/*  90 */     RoleDto dto = new RoleDto();
/*  91 */     if (po != null) {
/*  92 */       dto.setFunctions(convertFunctionPoToDto(po.getFunctions()));
/*  93 */       dto.setOid(po.getOid());
/*  94 */       dto.setRoleName(po.getRoleName());
/*  95 */       dto.setRoleLevel(po.getRoleLevel());
/*  96 */       dto.setDescription(po.getDescription());
/*  97 */       dto.setLastEditTime(po.getLastEditTime());
/*  98 */       dto.setLastEditUserName(po.getLastEditUserName());
/*  99 */       dto.setActive(po.isActive());
/*     */     }
/* 101 */     return dto;
/*     */   }
/*     */   
/*     */   private FunctionDto convertPoToDto(FunctionPo po, Map<String, FunctionDto> map) {
/* 105 */     if (map == null)
/* 106 */       map = new HashMap();
/* 107 */     FunctionDto dto = new FunctionDto();
/* 108 */     if ((po != null) && 
/* 109 */       ((dto = (FunctionDto)map.get(po.getOid())) == null)) {
/* 110 */       dto = new FunctionDto();
/* 111 */       dto.setAccessPath(po.getAccessPath());
/* 112 */       dto.setOid(po.getOid());
/* 113 */       dto.setFunctionName(po.getFunctionName());
/* 114 */       dto.setMethodPath(po.getMethodPath());
/* 115 */       dto.setGroup(po.isGroup());
/* 116 */       map.put(dto.getOid(), dto);
/* 117 */       dto.setChildFunctions(convertFunctionPoToDto(po.getChildFunctions(), map));
/* 118 */       dto.setParentFunctions(convertFunctionPoToDto(po.getParentFunctions(), map));
/*     */     }
/*     */     
/* 121 */     return dto;
/*     */   }
/*     */   
/* 124 */   public FunctionDto convertPoToDto(FunctionPo po) { return convertPoToDto(po, null); }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<UserDto> convertUserPoToDto(List<UserPo> pos)
/*     */   {
/* 130 */     List<UserDto> back = new ArrayList();
/* 131 */     for (UserPo po : pos) {
/* 132 */       back.add(convertPoToDto(po));
/*     */     }
/* 134 */     return back;
/*     */   }
/*     */   
/*     */   public List<UnitDto> convertUnitPoToDto(List<UnitPo> pos) {
/* 138 */     List<UnitDto> back = new ArrayList();
/* 139 */     for (UnitPo po : pos) {
/* 140 */       back.add(convertPoToDto(po));
/*     */     }
/* 142 */     return back;
/*     */   }
/*     */   
/*     */   public List<RoleDto> convertRolePoToDto(List<RolePo> pos) {
/* 146 */     List<RoleDto> back = new ArrayList();
/* 147 */     for (RolePo po : pos) {
/* 148 */       back.add(convertPoToDto(po));
/*     */     }
/* 150 */     return back;
/*     */   }
/*     */   
/*     */   private List<FunctionDto> convertFunctionPoToDto(List<FunctionPo> pos, Map<String, FunctionDto> map) {
/* 154 */     if (map == null)
/* 155 */       map = new HashMap();
/* 156 */     List<FunctionDto> back = new ArrayList();
/* 157 */     FunctionDto dto = new FunctionDto();
/* 158 */     for (FunctionPo po : pos) {
/* 159 */       if ((dto = (FunctionDto)map.get(po.getOid())) == null)
/* 160 */         dto = convertPoToDto(po, map);
/* 161 */       back.add(dto);
/*     */     }
/* 163 */     return back;
/*     */   }
/*     */   
/*     */   public List<FunctionDto> convertFunctionPoToDto(List<FunctionPo> pos) {
/* 167 */     return convertFunctionPoToDto(pos, null);
/*     */   }
/*     */   
/*     */ 
/*     */   public UserPo convertDtoToPo(UserDto dto)
/*     */   {
/* 173 */     UserPo po = new UserPo();
/* 174 */     if (dto != null) {
/* 175 */       po.setEmail(dto.getEmail());
/* 176 */       po.setPassword(dto.getPassword());
/* 177 */       po.setRoles(convertRoleDtoToPo(dto.getRoles()));
/* 178 */       po.setOid(dto.getOid());
/* 179 */       po.setUserName(dto.getUserName());
/* 180 */       po.setUnit(convertDtoToPo(dto.getUnit()));
/* 181 */       po.setActive(dto.isActive());
/* 182 */       po.setLoginId(dto.getLoginId());
/* 183 */       po.setPhone(dto.getPhone());
/* 184 */       po.setCreateTime(dto.getCreateTime());
/* 185 */       po.setLastUpdateTime(dto.getLastUpdateTime());
/*     */     }
/* 187 */     return po;
/*     */   }
/*     */   
/*     */   public UnitPo convertDtoToPo(UnitDto dto) {
/* 191 */     UnitPo po = new UnitPo();
/* 192 */     if (dto != null) {
/* 193 */       po.setOtherMonitorUnits(convertUnitDtoToPo(dto.getOtherMonitorUnits()));
/* 194 */       if (dto.isRoot()) {
/* 195 */         po.setParentUnitID("ROOT");
/*     */       } else {
/* 197 */         po.setParentUnitID(dto.getParent().getOid());
/*     */       }
/* 199 */       po.setUnitAlias(dto.getUnitAlias());
/* 200 */       po.setOid(dto.getOid());
/* 201 */       po.setUnitName(dto.getUnitName());
/* 202 */       po.setUnitPathName(dto.getUnitPathName());
/*     */     }
/* 204 */     return po;
/*     */   }
/*     */   
/*     */   public RolePo convertDtoToPo(RoleDto dto) {
/* 208 */     RolePo po = new RolePo();
/* 209 */     if (dto != null) {
/* 210 */       po.setFunctions(convertFunctionDtoToPo(dto.getFunctions()));
/* 211 */       po.setOid(dto.getOid());
/* 212 */       po.setRoleName(dto.getRoleName());
/* 213 */       po.setRoleLevel(dto.getRoleLevel());
/* 214 */       po.setDescription(dto.getDescription());
/* 215 */       po.setLastEditTime(dto.getLastEditTime());
/* 216 */       po.setLastEditUserName(dto.getLastEditUserName());
/*     */     }
/* 218 */     return po;
/*     */   }
/*     */   
/*     */   public FunctionPo convertDtoToPo(FunctionDto dto) {
/* 222 */     FunctionPo po = new FunctionPo();
/* 223 */     if (dto != null) {
/* 224 */       po.setAccessPath(dto.getAccessPath());
/* 225 */       po.setOid(dto.getOid());
/* 226 */       po.setFunctionName(dto.getFunctionName());
/* 227 */       po.setMethodPath(dto.getMethodPath());
/*     */     }
/* 229 */     return po;
/*     */   }
/*     */   
/*     */ 
/*     */   public List<UserPo> convertUserDtoToPo(List<UserDto> dtos)
/*     */   {
/* 235 */     List<UserPo> back = new ArrayList();
/* 236 */     for (UserDto dto : dtos) {
/* 237 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 239 */     return back;
/*     */   }
/*     */   
/*     */   public List<UnitPo> convertUnitDtoToPo(List<UnitDto> dtos) {
/* 243 */     List<UnitPo> back = new ArrayList();
/* 244 */     for (UnitDto dto : dtos) {
/* 245 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 247 */     return back;
/*     */   }
/*     */   
/*     */   public List<RolePo> convertRoleDtoToPo(List<RoleDto> dtos) {
/* 251 */     List<RolePo> back = new ArrayList();
/* 252 */     for (RoleDto dto : dtos) {
/* 253 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 255 */     return back;
/*     */   }
/*     */   
/*     */   public List<FunctionPo> convertFunctionDtoToPo(List<FunctionDto> dtos) {
/* 259 */     List<FunctionPo> back = new ArrayList();
/* 260 */     for (FunctionDto dto : dtos) {
/* 261 */       back.add(convertDtoToPo(dto));
/*     */     }
/* 263 */     return back;
/*     */   }
/*     */   
/*     */ 
/*     */   public UserPo convertFormToPo(UserForm form)
/*     */   {
/* 269 */     UserPo po = new UserPo();
/* 270 */     if (form != null) {
/* 271 */       if ((this.unitSvc != null) && (this.roleSvc != null)) {
/* 272 */         po.setEmail(form.getEmail());
/* 273 */         po.setPassword(form.getPassword());
/* 274 */         po.setUnit(this.unitSvc.findByOid(form.getUnitID()));
/* 275 */         po.setOid(form.getOid());
/* 276 */         po.setUserName(form.getUserName());
/* 277 */         List<RolePo> roles = new ArrayList();
/* 278 */         for (String rID : form.getRoleIDs()) {
/* 279 */           roles.add(this.roleSvc.findByOid(rID));
/*     */         }
/* 281 */         po.setRoles(roles);
/* 282 */         po.setPhone(form.getPhone());
/* 283 */         po.setCreateTime(form.getCreateTime());
/* 284 */         po.setLastUpdateTime(form.getLastUpdateTime());
/*     */       } else {
/* 286 */         log.error("取不到UnitService與RoleService");
/*     */       }
/*     */     }
/* 289 */     return po;
/*     */   }
/*     */   
/*     */   public UnitPo convertFormToPo(UnitForm form) {
/* 293 */     UnitPo po = new UnitPo();
/* 294 */     if (form != null) {
/* 295 */       if (this.unitSvc != null) {
/* 296 */         if (form.getIsRoot().equals("true")) {
/* 297 */           po.setParentUnitID("ROOT");
/* 298 */           po.setUnitPathName("");
/*     */         } else {
/* 300 */           UnitPo parent = this.unitSvc.findByOid(form.getParentID());
/* 301 */           po.setParentUnitID(parent.getOid());
/* 302 */           po.setUnitPathName(parent.getUnitPathName() + parent.getUnitName());
/*     */         }
/* 304 */         po.setUnitAlias(form.getUnitAlias());
/* 305 */         po.setOid(form.getOid());
/* 306 */         po.setUnitName(form.getUnitName());
/*     */       } else {
/* 308 */         log.error("取不到UnitService");
/*     */       }
/*     */     }
/* 311 */     return po;
/*     */   }
/*     */   
/*     */   public RolePo convertFormToPo(RoleForm form) {
/* 315 */     RolePo po = new RolePo();
/* 316 */     if (form != null) {
/* 317 */       if (this.funSvc != null) {
/* 318 */         po.setOid(form.getOid());
/* 319 */         po.setRoleName(form.getRoleName());
/* 320 */         List<FunctionPo> functions = new ArrayList();
/* 321 */         for (String fID : form.getFunctionIDs()) {
/* 322 */           functions.add(this.funSvc.findByOid(fID));
/*     */         }
/* 324 */         po.setFunctions(functions);
/*     */       } else {
/* 326 */         log.error("取不到FunctionService");
/*     */       }
/*     */     }
/* 329 */     return po;
/*     */   }
/*     */   
/*     */   public FunctionPo convertFormToPo(FunctionForm form) {
/* 333 */     FunctionPo po = new FunctionPo();
/* 334 */     if (form != null) {
/* 335 */       if (this.funSvc != null) {
/* 336 */         po.setAccessPath(form.getAccessPath());
/* 337 */         po.setOid(form.getOid());
/* 338 */         po.setFunctionName(form.getFunctionName());
/* 339 */         po.setMethodPath(form.getMethodPath());
/* 340 */         po.setOid(form.getOid());
/* 341 */         List<FunctionPo> functions = new ArrayList();
/* 342 */         for (String fID : form.getFunctionIDs()) {
/* 343 */           functions.add(this.funSvc.findByOid(fID));
/*     */         }
/* 345 */         po.setChildFunctions(functions);
/* 346 */         if (form.getIsGroup().equals("true")) {
/* 347 */           po.setGroup(true);
/*     */         } else {
/* 349 */           po.setGroup(false);
/*     */         }
/*     */       } else {
/* 352 */         log.error("取不到FunctionService");
/*     */       }
/*     */     }
/* 355 */     return po;
/*     */   }
/*     */   
/*     */ 
/*     */   public UserForm convertPoToForm(UserPo po)
/*     */   {
/* 361 */     UserForm form = new UserForm();
/* 362 */     if (po != null) {
/* 363 */       form.setEmail(po.getEmail());
/* 364 */       form.setPassword(po.getPassword());
/* 365 */       form.setUnitID(po.getUnit().getOid());
/* 366 */       form.setUnitID(po.getOid());
/* 367 */       form.setUserName(po.getUserName());
/* 368 */       List<String> roles = new ArrayList();
/* 369 */       for (RolePo rpo : po.getRoles()) {
/* 370 */         roles.add(rpo.getOid());
/*     */       }
/* 372 */       form.setRoleIDs((String[])roles.toArray(new String[0]));
/* 373 */       form.setPhone(po.getPhone());
/* 374 */       form.setCreateTime(po.getCreateTime());
/* 375 */       form.setLastUpdateTime(po.getLastUpdateTime());
/*     */     }
/* 377 */     return form;
/*     */   }
/*     */   
/*     */   public UnitForm convertPoToForm(UnitPo po) {
/* 381 */     UnitForm form = new UnitForm();
/* 382 */     if (po != null) {
/* 383 */       if (po.getParentUnitID().equals("ROOT")) {
/* 384 */         form.setIsRoot("true");
/*     */       } else {
/* 386 */         form.setIsRoot("false");
/*     */       }
/* 388 */       form.setParentID(po.getParentUnitID());
/* 389 */       form.setUnitAlias(po.getUnitAlias());
/* 390 */       form.setOid(po.getOid());
/* 391 */       form.setUnitName(po.getUnitName());
/*     */     }
/* 393 */     return form;
/*     */   }
/*     */   
/*     */   public RoleForm convertPoToForm(RolePo po) {
/* 397 */     RoleForm form = new RoleForm();
/* 398 */     if (po != null) {
/* 399 */       form.setOid(po.getOid());
/* 400 */       form.setRoleName(po.getRoleName());
/* 401 */       List<String> functionIDs = new ArrayList();
/* 402 */       for (FunctionPo fpo : po.getFunctions()) {
/* 403 */         functionIDs.add(fpo.getOid());
/*     */       }
/* 405 */       form.setFunctionIDs((String[])functionIDs.toArray(new String[0]));
/*     */     }
/* 407 */     return form;
/*     */   }
/*     */   
/*     */   public FunctionForm convertPoToForm(FunctionPo po) {
/* 411 */     FunctionForm form = new FunctionForm();
/* 412 */     if (po != null) {
/* 413 */       form.setAccessPath(po.getAccessPath());
/* 414 */       form.setOid(po.getOid());
/* 415 */       form.setFunctionName(po.getFunctionName());
/* 416 */       form.setMethodPath(po.getMethodPath());
/* 417 */       List<String> functionIDs = new ArrayList();
/* 418 */       for (FunctionPo fpo : po.getChildFunctions()) {
/* 419 */         functionIDs.add(fpo.getOid());
/*     */       }
/* 421 */       form.setFunctionIDs((String[])functionIDs.toArray(new String[0]));
/* 422 */       if (po.isGroup()) {
/* 423 */         form.setIsGroup("true");
/*     */       } else {
/* 425 */         form.setIsGroup("false");
/*     */       }
/*     */     }
/* 428 */     return form;
/*     */   }
/*     */ }


/* Location:              D:\MOI\OPENDATASOURCECODE\open-data\WebContent\WEB-INF\lib\opd.Ver769.jar!\com\iisi\opd\auth\dto\DtoPoFormTransformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */