package com.rotterdam.model.dao;

import com.rotterdam.model.entity.Day;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Created by vasax32 on 17.01.15.
 */
@Named
@Scope("singleton")
public class DayDao extends AbstractGenericDao<Day> {



}
