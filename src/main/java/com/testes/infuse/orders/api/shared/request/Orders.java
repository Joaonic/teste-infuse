package com.testes.infuse.orders.api.shared.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Orders")
public class Orders {

    @NotEmpty
    @Valid
    @JsonProperty("orders")
    @JacksonXmlElementWrapper(useWrapping = false, localName = "order")
    @JacksonXmlProperty(localName = "order")
    private List<OrderRequest> orderList;
}
