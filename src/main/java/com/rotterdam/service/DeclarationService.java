package com.rotterdam.service;

import com.rotterdam.dto.DayDeclarationDto;
import com.rotterdam.dto.DeclarationsDto;
import com.rotterdam.model.dao.DeclarationDao;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.Declaration;
import com.rotterdam.model.entity.Period;
import com.rotterdam.model.entity.Week;
import com.rotterdam.tools.DateTools;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

    @Inject
    private PeriodDao periodDao;

    @Transactional
    public DeclarationsDto selectByWeekIdAndUserId(Date date, long userId){
        Date monday = DateTools.getDateOfPrevMonday(date);

        Week week = weekDao.selectByStartDateAndUser(monday, userId);
        if(week != null) {
            List<Day> days = week.getDays();
            if(days != null){
                DeclarationsDto declarationsDto = new DeclarationsDto();
                for (Day day : days){
                    DayDeclarationDto dayDeclarationDto = new DayDeclarationDto(day);
                    declarationsDto.daysDeclaration.add(dayDeclarationDto);
                }
                return declarationsDto;
            }
        }
        return null;
    }

    @Transactional
    public void save(DeclarationsDto declarationsDto, long userId){
        Date monday = DateTools.getDateOfPrevMonday(declarationsDto.date);

        Week week = weekDao.selectByStartDateAndUser(monday, userId);

        for (int i = 0; i < 7; i++) {
            Day day = week.getDays().get(i);
            List<Declaration> declarations = day.getDeclarations();
            DayDeclarationDto dayDeclarationDto = declarationsDto.daysDeclaration.get(i);
            if (!declarationEquals(declarations, dayDeclarationDto.declarations)) {
                //lazy version
                for (Declaration declaration : declarations)
                    declarationDao.delete(declaration);

                for (Declaration declaration : dayDeclarationDto.declarations) {
                    if(declaration.getPrice() != 0) {
                        declaration.setDay(day);
                        declarationDao.insert(declaration);
                    }
                }
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
//            if(!firstDeclaration.getDate().equals(secondDeclaration.getDate()))
//                return false;
        }
        return true;
    }

    public List<DayDeclarationDto> getFakeDeclarations(Date monday) {
        List<DayDeclarationDto> days = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            DayDeclarationDto day = new DayDeclarationDto();
            day.date = DateTools.getDatePlusDays(monday, i);
            days.add(day);
        }
        return days;
    }

    @Transactional
    public boolean isActive(Date weekDate, long userId){
        Period weekPeriod = periodDao.selectByDateBetweenAndUser(weekDate, userId);
        Period currentPeriod = periodDao.selectByDateBetweenAndUser(new Date(), userId);
        if(weekPeriod == null || currentPeriod == null) return false;
        return weekPeriod.getStartDate().equals(currentPeriod.getStartDate());
    }
}
