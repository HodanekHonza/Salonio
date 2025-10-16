package com.salonio.modules.business.application.port.business.out;

import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;

public interface BusinessEventPort {

    void publishBusinessSchedulingEvent(BusinessSchedulingEvent businessSchedulingEvent);

}
