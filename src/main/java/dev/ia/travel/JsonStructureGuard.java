package dev.ia.travel;

import java.io.StringReader;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class JsonStructureGuard implements OutputGuardrail {

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        String response = aiMessage.text();
        try (JsonReader reader = Json.createReader(new StringReader(response))) {
            JsonObject jsonObject = reader.readObject();
            return OutputGuardrailResult.success();
        } catch (Exception e) {
            // Ensinamos o modelo como corrigir.
            // importante adicionar um max retries
            return reprompt(aiMessage.text(), """
                    Erro: Sua resposta não é um JSON válido.
                    Problema encontrado: %s
                    Gere NOVAMENTE apenas o JSON, sem blocos de código markdown ou texto adicional.
                    """.formatted(e.getMessage()));
        }
    }

}
