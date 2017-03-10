/*
 * Copyright 2017 Hewlett Packard Enterprise Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

define([
    'underscore',
    './saved-search-widget',
    'find/app/configuration',
    'find/app/model/documents-collection',
    'text!find/idol/templates/page/dashboards/widgets/video-widget.html'
], function(_, SavedSearchWidget, configuration, DocumentsCollection, template) {
    'use strict';

    return SavedSearchWidget.extend({
        viewType: 'list',

        initialize: function(options) {
            SavedSearchWidget.prototype.initialize.apply(this, arguments);

            this.videoTemplate = _.template(template);
            this.loop = options.widgetSettings.loop !== false;
            this.audio = options.widgetSettings.audio || false;
            this.searchResultNumber = options.widgetSettings.searchResultNumber || 1;
            this.restrictSearch = Boolean(options.widgetSettings.restrictSearch);

            this.documentsCollection = new DocumentsCollection();
        },

        render: function() {
            SavedSearchWidget.prototype.render.apply(this);

            this.listenTo(this.documentsCollection, 'add', function(model) {
                if(model.get('media') === 'video') {
                    const url = model.get('url');
                    const offset = model.get('offset');
                    const src = offset ? url + '#t=' + offset : url;

                    this.$content.html(this.videoTemplate({
                        loop: this.loop,
                        muted: !this.audio,
                        src: src
                    }));
                    if(this.updateCallback) {
                        this.updateCallback();
                        delete this.updateCallback();
                    }
                }
            });

            this.getData();
        },

        getData: function() {
            let fieldText = this.queryModel.get('fieldText');

            if(this.restrictSearch) {
                const restrictToVideo = 'MATCH{video}:' + configuration().fieldsInfo.contentType.names[0];
                fieldText = fieldText
                    ? fieldText + ' AND ' + restrictToVideo
                    : restrictToVideo;
            }

            return this.documentsCollection.fetch({
                data: {
                    start: this.searchResultNumber,
                    text: this.queryModel.get('queryText'),
                    max_results: this.searchResultNumber,
                    indexes: this.queryModel.get('indexes'),
                    field_text: fieldText,
                    min_date: this.queryModel.get('minDate'),
                    max_date: this.queryModel.get('maxDate'),
                    sort: 'relevance',
                    summary: 'context',
                    queryType: 'MODIFIED'
                },
                reset: false
            });
        }
    });
});
