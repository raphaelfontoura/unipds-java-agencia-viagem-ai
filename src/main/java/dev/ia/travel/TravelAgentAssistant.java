package dev.ia.travel;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface TravelAgentAssistant {

    /*
     * Chat with the travel agent assistant with response from LLM.
     * @param userMessage the user's message
     * @return the assistant's response
     */
    String chat(String userMessage);

}
