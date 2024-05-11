package com.testes.infuse.orders.api.shared.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.testes.infuse.orders.core.port.in.dto.OrderRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {

    @NotEmpty
    @Valid
    @JacksonXmlElementWrapper(useWrapping = false, localName = "order")
    @JacksonXmlProperty(localName = "order")
    private List<OrderRequest> orders;
}
