package dev.ia.travel;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped 
public class ToneGuardrail implements OutputGuardrail {

    @Inject
    ToneJudge judge;

    @Override
    public OutputGuardrailResult validate(AiMessage responseFromLLM) {
        if (!judge.isProfessional(responseFromLLM.text())) {
            return reprompt(responseFromLLM.text(), """
                    Erro: Sua resposta não está no tom profissional esperado.
                    Por favor, gere NOVAMENTE a resposta mantendo o mesmo conteúdo, mas com um tom mais profissional.
                    """);
        }
        return OutputGuardrailResult.success();
    }
}
