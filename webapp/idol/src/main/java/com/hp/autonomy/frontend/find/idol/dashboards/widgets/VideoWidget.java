package com.hp.autonomy.frontend.find.idol.dashboards.widgets;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.hp.autonomy.frontend.find.idol.dashboards.widgets.datasources.WidgetDatasource;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("WeakerAccess")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonDeserialize(builder = VideoWidget.VideoWidgetBuilder.class)
public class VideoWidget extends DatasourceDependentWidgetBase<VideoWidget, VideoWidgetSettings> {
    @SuppressWarnings("ConstructorWithTooManyParameters")
    @Builder(toBuilder = true)
    public VideoWidget(final String name, final String type, final Integer x, final Integer y, final Integer width, final Integer height, final WidgetDatasource<?> datasource, final VideoWidgetSettings widgetSettings) {
        super(name, type, x, y, width, height, datasource, widgetSettings);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class VideoWidgetBuilder {
    }
}