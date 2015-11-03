package com.sebek.dao;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sebek.model.AccountRole;
import com.sebek.dao.AbstractDao;

@Transactional
@Repository("AccountRoleDao")
public class AccountRoleDaoImpl extends AbstractDao<AccountRole, Id> implements AccountRoleDao {

}