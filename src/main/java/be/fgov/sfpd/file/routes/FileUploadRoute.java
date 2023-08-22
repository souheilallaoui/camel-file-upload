package be.fgov.sfpd.file.routes;

import org.apache.camel.Exchange;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileUploadRoute extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploadRoute.class);

    @Override
    public void configure() throws Exception {
    //@formatter:off

        from("servlet:multipart?servletName=FileUploadServlet&attachmentMultipartBinding=true")
            .process(this::getNumberOfAttachments)
            .log("Number of attachments: ${header.numberOfAttachments}");

            
    //@formatter:on
    }

    private void getNumberOfAttachments(Exchange exchange) {
        AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
        int numberOfAttachments = 0;

        if (attMsg.hasAttachments()) {
            numberOfAttachments = attMsg.getAttachments().size();
        }

        exchange.getMessage().setHeader("numberOfAttachments", numberOfAttachments);        
    }


}
