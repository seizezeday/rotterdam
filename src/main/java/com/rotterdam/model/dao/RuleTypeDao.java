package com.rotterdam.model.dao;

import com.rotterdam.model.entity.RuleType;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Created by root on 17.01.15.
 */
@Named
@Scope("singleton")
public class RuleTypeDao extends AbstractGenericDao<RuleType>{
}
