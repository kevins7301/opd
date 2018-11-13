/*     */ package com.iisi.opd.exception.util;

/*     */ import java.sql.SQLException;

/*     */ import javax.persistence.EntityExistsException;
/*     */ import javax.persistence.RollbackException;

/*     */ import org.aspectj.lang.JoinPoint;
/*     */ import org.aspectj.lang.annotation.AfterThrowing;
/*     */ import org.aspectj.lang.annotation.Aspect;
/*     */ import org.aspectj.lang.annotation.Before;
/*     */ import org.aspectj.lang.annotation.Pointcut;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.dao.DuplicateKeyException;
/*     */ import org.springframework.jdbc.BadSqlGrammarException;
/*     */ import org.springframework.orm.jpa.JpaSystemException;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.transaction.TransactionSystemException;

/*     */
/*     */ import com.iisi.opd.exception.DefineException;
/*     */ import com.iisi.opd.exception.msg.DaoMsgSeq;
/*     */ import com.iisi.opd.exception.msg.MsgLevel;
/*     */ import com.iisi.opd.exception.msg.MsgType;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ @Aspect
/*     */ @Component
/*     */ public class ExceptionAdvice
/*     */ {
    /*  38 */ public static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /*     */
    /*  40 */ private static enum ExceptionType {
        JpaSystemException, /*  41 */ TransactionSystemException, /*  42 */ DataAccessException, /*  43 */ PropertyAccessException, /*  44 */ OptimisticLockException, /*  45 */ EntityExistsException, /*  46 */ IllegalStateException, /*  47 */ IllegalArgumentException, /*  48 */ TransactionRequiredException, /*  49 */ RollbackException, /*  50 */ PersistenceException, /*  51 */ DefineException, /*  52 */ OpdException, /*  53 */ UNKNOWNEXCEPTIONTYPE;
        /*     */
        /*     */ private ExceptionType() {
        }
        /*     */ }

    /*     */
    /*     */ @Before("logAnyMethod()&& args(name)")
    /*     */ public void logAccessCheck(String name) {
    }

    /*     */
    /*     */ private static ExceptionType lookupException(Exception ex)
    /*     */ {
        /*  63 */ ExceptionType type = null;
        /*     */ try {
            /*  65 */ String exName = ex.getClass().getSimpleName();
            /*  66 */ type = ExceptionType.valueOf(exName);
            /*     */ } catch (IllegalArgumentException exception) {
            /*  68 */ logger.info("[UNKNOWNEXCEPTIONTYPE]No enum const{" + ex + "}");
            /*  69 */ type = ExceptionType.UNKNOWNEXCEPTIONTYPE;
            /*     */ }
        /*  71 */ return type;
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ @Pointcut("execution(* *..service.*Service.*(..))")
    /*     */ public void logAnyMethod() {
    }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ @AfterThrowing(pointcut = "logAnyMethod()", throwing = "ex")
    /*     */ public void logAfterThrowing(JoinPoint joinPoint, Exception ex)/*     */ throws DefineException, Exception
    /*     */ {
        /*  91 */ logger.error(ex.getMessage(), ex);
        /*     */
        /*  93 */ ExceptionType exceptionType = lookupException(ex);
        /*  94 */ logger.error("ExceptionType：{} ", exceptionType);
        /*  95 */ switch (exceptionType)
        /*     */ {
            /*     */ case OpdException:
                /*     */
                /*     */ case JpaSystemException:
                /* 100 */ Throwable e1 = ((JpaSystemException) ex).getRootCause();
                /* 101 */ if ((e1 instanceof SQLException)) {
                    /* 102 */ SQLException sqlException = (SQLException) e1;
                    /* 103 */ int oracleErrorCode = sqlException.getErrorCode();
                    /* 104 */ checkSqlState(oracleErrorCode, sqlException);
                }
                /* 105 */ break;
            /*     */
            /*     */
            /*     */ case TransactionSystemException:
                /* 109 */ Throwable ex2 = ((TransactionSystemException) ex).getRootCause();
                /* 110 */ if ((ex2 instanceof SQLException)) {
                    /* 111 */ SQLException sqlException = (SQLException) ex2;
                    /* 112 */ int oracleErrorCode = sqlException.getErrorCode();
                    /* 113 */ checkSqlState(oracleErrorCode, sqlException);
                    /*     */ }
                /*     */
                /*     */
                /*     */ case DataAccessException:
                /* 118 */ if (((org.springframework.dao.DataAccessException) ex).contains(DuplicateKeyException.class))
                /*     */ {
                    /* 120 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(),
                            MsgType.DATABASE, DaoMsgSeq.DUPLICATE, MsgLevel.ERROR, ex);
                    /*     */ }
                /*     */
                /*     */
                /* 124 */ if (((org.springframework.dao.DataAccessException) ex).contains(BadSqlGrammarException.class))
                /*     */ {
                    /* 126 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(),
                            MsgType.DATABASE, DaoMsgSeq.BAD_SQL_GRAMMAR, MsgLevel.ERROR, ex);
                    /*     */ }
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */ case PropertyAccessException:
                /* 133 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.ARGUMENTEXCEPTION, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */ case OptimisticLockException:
                /* 139 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.OPTIMISTICLOCKEXCEPTION, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */ case EntityExistsException:
                /* 145 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.DUPLICATE, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */ case IllegalStateException:
                /* 151 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.ENTITYCLOSED, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */ case IllegalArgumentException:
                /* 156 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.NOTAENTITY, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */ case TransactionRequiredException:
                /* 161 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.TRANSACTIONERR, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */ case RollbackException:
                /* 166 */ Throwable rollbackException = ((RollbackException) ex).getCause();
                /*     */
                /* 168 */ if ((rollbackException instanceof EntityExistsException)) {
                    /* 169 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(),
                            MsgType.DATABASE, DaoMsgSeq.DUPLICATE, MsgLevel.ERROR, ex);
                    /*     */ }
                /*     */
                /*     */
                /* 173 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.ROLLBACK, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */
                /*     */ case PersistenceException:
                /* 179 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.PERSISTENCE, MsgLevel.ERROR, ex);
                /*     */
                /*     */
                /*     */
                /*     */ case DefineException:
                /* 184 */ throw ex;
                /*     */
                /*     */ case UNKNOWNEXCEPTIONTYPE:
                /* 187 */ logger.error("unknown exception type{} " + ex.getStackTrace());
                /* 188 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                        DaoMsgSeq.UNKNOWNEXCEPTIONTYPE, MsgLevel.ERROR, ex);
                /*     */ }
        /*     */
        /*     */ }

    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ private void checkSqlState(int oracleErrorCode, SQLException sqlException)
    /*     */ {
        /* 200 */ logger.error("[CheckSqlState]" + sqlException.getMessage());
        /* 201 */ if (oracleErrorCode == Constant.OracleErrorCode.DUPLICATE.getErrorCode())
        /*     */ {
            /*     */
            /* 204 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                    DaoMsgSeq.DUPLICATE, MsgLevel.ERROR, sqlException);
            /*     */ }
        /*     */
        /*     */
        /* 208 */ if (oracleErrorCode == Constant.OracleErrorCode.VALUETOOLARGE.getErrorCode())
        /*     */ {
            /* 210 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                    DaoMsgSeq.VALUETOOLARGE, MsgLevel.ERROR, sqlException);
            /*     */ }
        /*     */
        /*     */
        /*     */
        /* 215 */ if (sqlException.getErrorCode() == 20) {
            /* 216 */ throw new RuntimeException(sqlException.getMessage());
            /*     */ }
        /* 218 */ logger.error("unknown exception type");
        /* 219 */ throw new com.iisi.opd.exception.DataAccessException(ExceptionAdvice.class.getName(), MsgType.DATABASE,
                DaoMsgSeq.UNDEFINEDSQLEXCEPTION, MsgLevel.ERROR, sqlException);
        /*     */ }
    /*     */ }
