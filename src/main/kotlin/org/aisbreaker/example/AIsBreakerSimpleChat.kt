package org.aisbreaker.example

import org.aisbreaker.api.AIsBreaker
import org.aisbreaker.api.AIsError
import org.aisbreaker.api.AIsService
import org.aisbreaker.api.AIsServiceProps
import org.aisbreaker.api.model.Auth
import org.aisbreaker.api.model.Input
import org.aisbreaker.api.model.InputText
import org.aisbreaker.api.model.Request
import org.aisbreaker.api.model.ResponseFinal

/**
 * AIsBreakerSimpleChat
 *
 * A simple chat example using AIsBreaker Java API.
 */
fun main() {
    println("AIsBreakerSimpleChat")
    println("--------------------")

    try {
        // service configuration: select the service/serviceId you want to use
        val serviceProps = AIsServiceProps()
            .setServiceId("chat:dummy")

            //.setServiceId("chat:openai.com")

            //.setServiceId("chat:gemini.vertexai.google.com")
            //.setProject("<YOUR-GOOGLE-CLOUD-PROJECT>")          // optional for gemini.vertexai.google.com
            //.setLocation("<YOUR-GOOGLE-CLOUD-LOCATION>")        // optional for gemini.vertexai.google.com, e.g. "us-central1"

            //.setServiceId("chat:huggingface.co/<HF-ACCOUNT>/<HF-MODEL>")
            // e.g.:
            //.setServiceId("chat:huggingface.co/microsoft/DialoGPT-large")

            //.setServiceId("aisbreaker:mirror")
            //.setForward2ServiceProps(AIsServiceProps()
            //    .setServiceId("chat:echo")
            //)

        // service initialization
        val aisbreakerServerURL: String? = null //"https://api.demo.aisbreaker.org/"
        val auth = Auth()
            // optionally, set your OpenAI API key:
            //.setSecret("sk-...")

            // optionally, set your Google Cloud (Vertext AI) API key:
            //.setSecret("googlecloud-account-json-base64_..")

            // optionally, set your Huggingface API key:
            //.setSecret("hf_...")

            // optionally, set your AIsBreaker API key:
            //.setSecret("aisbreaker_...")
        val aisService = AIsBreaker.getAIsService(aisbreakerServerURL, serviceProps, auth)


        // 1st question/prompt
        val question1 = "What is NodeJS?"
        println("***** Question1 *****\n$question1\n")

        // 1st answer
        val response1 = aisService.process(Request()
            .addInput(Input()
                .setText(InputText()
                    .setRole("user")
                    .setContent(question1)
                )
            )
        )
        println("***** Answer1 *****\n${response1.outputs[0].text.content}\n")


        // 2nd question/prompt
        val question2 = "shorter please"
        println("***** Question2 *****\n$question2\n")

        // 2nd answer
        val response2 = aisService.process(Request()
            .addInput(Input()
                .setText(InputText()
                    .setRole("user")
                    .setContent(question2)
                )
            )
            .setConversationState(response1.conversationState)
        )
        println("***** Answer2 *****\n${response2.outputs[0].text.content}\n")

    } catch (e: AIsError) {
        e.printStackTrace()
    }
}
