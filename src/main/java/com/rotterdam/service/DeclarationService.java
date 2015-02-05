package com.rotterdam.service;

import com.rotterdam.dto.DeclarationsDto;
import com.rotterdam.model.dao.DeclarationDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.entity.Declaration;
import com.rotterdam.model.entity.Week;
import com.rotterdam.tools.DateTools;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 29.01.15.
 */
@Named
public class DeclarationService {

    @Inject
    private DeclarationDao declarationDao;

    @Inject
    private WeekDao weekDao;

    @Transactional
    public DeclarationsDto selectByWeekIdAndUserId(Date date, long userId){
        Date monday = DateTools.getDateOfPrevMonday(date);

        Week week = weekDao.selectByStartDateAndUser(monday, userId);
        if(week != null)
            return new DeclarationsDto(declarationDao.selectByStartDateAndUser(week.getIdWeek(), userId));
        return null;
    }

    @Transactional
    public void save(DeclarationsDto declarationsDto, long userId){
        Date monday = DateTools.getDateOfPrevMonday(declarationsDto.date);

        Week week = weekDao.selectByStartDateAndUser(monday, userId);

        if(!declarationEquals(week.getDeclarations(), declarationsDto.declarations)){
            //lazy version
            for (Declaration declaration : week.getDeclarations())
                declarationDao.delete(declaration);

            for(Declaration declaration : declarationsDto.declarations){
                declaration.setWeek(week);
                declarationDao.insert(declaration);
            }
        }
    }

    private boolean declarationEquals(List<Declaration> first, List<Declaration> second){
        if(first.size() != second.size()) return false;
        for (int i = 0; i < first.size(); i++){
            Declaration firstDeclaration  =first.get(i);
            Declaration secondDeclaration = second.get(i);
            if(!firstDeclaration.getCostType().equals(secondDeclaration.getCostType()))
                return false;
            if(Double.compare(firstDeclaration.getPrice(), secondDeclaration.getPrice()) != 0)
                return false;
            if(!firstDeclaration.getDate().equals(secondDeclaration.getDate()))
                return false;
        }
        return true;
    }
}
