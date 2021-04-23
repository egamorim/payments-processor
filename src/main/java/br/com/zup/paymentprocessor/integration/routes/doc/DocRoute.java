package br.com.zup.paymentprocessor.integration.routes.doc;

import br.com.zup.paymentprocessor.integration.config.Route;
import br.com.zup.paymentprocessor.integration.processors.DocProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;

@Route
@RequiredArgsConstructor
public class DocRoute extends RouteBuilder {

    private final DocProcessor docProcessor;

    @Override
    public void configure() throws Exception {

        from("direct:doc")
                .process(docProcessor)
                .log("New DOC processed")
                .end();

    }

}
