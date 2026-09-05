package dev.ia.travel;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.guardrail.InputGuardrail;
import dev.langchain4j.guardrail.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped 
public class InjectionGuard implements InputGuardrail {

    @Inject 
    PromptSecurityExpert securityExpert;

    @Override 
    public InputGuardrailResult validate(UserMessage userMessage) {
        if (securityExpert.isAttack(userMessage.singleText())) {
            return failure("Sua mensagem foi bloqueada porque foi detectada como um possível ataque. Por favor, reformule sua pergunta.");
        }
        return success();
    }

}
