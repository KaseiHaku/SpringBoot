package kasei.boot.security.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;

@JsonComponent
public class GrantedAuthorityConverter {

    public static class GrantedAuthoritySerializer extends JsonSerializer<GrantedAuthority> {
        @Override
        public void serialize(GrantedAuthority grantedAuthority, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (grantedAuthority != null) {
                jsonGenerator.writeString(grantedAuthority.getAuthority());
            } else {
                jsonGenerator.writeNull();
            }
        }
    }
}
