package org.mintcode.errabbit.core.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mintcode.errabbit.core.report.dao.ReportDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by soleaf on 8/23/15.
 */
@Component
public class ReportScheduler {

    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    ReportDescriptionRepository repository;

    @Scheduled(cron = "0 * * * * * *")
    public void check(){
        try{
            List<ReportDescription> descriptionList = repository.findAll();
            if (descriptionList == null && descriptionList.isEmpty()){
                logger.trace("have no report description");
                return;
            }

            ReportDescription description = descriptionList.get(0);
            if (!description.getActive()){
                logger.trace("description is not active");
                return;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            Integer nowHour = cal.get(Calendar.HOUR_OF_DAY);
            if (nowHour == description.getTime().getHour()){
                // todo : Generate report
            }
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

}