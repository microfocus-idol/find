/*
 * Copyright 2020 Micro Focus International plc.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

define([
    'find/app/model/find-base-collection'
], function (FindBaseCollection) {
    'use strict';

    return FindBaseCollection.extend({
        url: 'api/public/themetracker/clusters',

        parse: function (response) {
            return [response];
        }
    });
});
