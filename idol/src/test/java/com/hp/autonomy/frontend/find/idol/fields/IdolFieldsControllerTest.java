/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.frontend.find.idol.fields;

import com.autonomy.aci.client.services.AciErrorException;
import com.hp.autonomy.frontend.find.core.fields.AbstractFieldsControllerTest;
import com.hp.autonomy.searchcomponents.idol.fields.IdolFieldsRequest;
import org.junit.Before;

public class IdolFieldsControllerTest extends AbstractFieldsControllerTest<IdolFieldsRequest, AciErrorException> {
    @Before
    public void setUp() {
        controller = new IdolFieldsController(service);
    }

    @Override
    protected IdolFieldsRequest createRequest() {
        return new IdolFieldsRequest.Builder().build();
    }
}
