# Stack Overflow RSS Feed

This project is a *Proof of Concept (POC)* of the **RxJava** library in the *Android* ecosystem. The implementation is mainly revolving around the *reactive* style of programming; the list of news is fetched from the *StackOverflow* endpoint and then displayed in the view after invoking the update in one of the overridden methods (*onNext*) of the *Observable*. Since the *API call* to obtain the RSS has to be made, which is the asynchronous operation, the **Retrofit** library was incorporated. Additionally, **TikXML** has been used to parse the *XML* response to *Java* models.

## External Libraries / Tools / Conventions
*   [**RxJava**](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM
*   [**Retrofit**](https://github.com/square/retrofit) - A type-safe HTTP client for Android and the JVM
*   [**TikXML**](https://github.com/Tickaroo/tikxml) - Modern XML Parser for Android
*   [**Lombok**](https://github.com/rzwitserloot/lombok) - Very spicy additions to the Java programming language
*   [**Apache Commons Text**](https://commons.apache.org/proper/commons-text/scm.html) - A library focused on algorithms working on strings
*   [**RecyclerView**](https://developer.android.com/guide/topics/ui/layout/recyclerview) - Display large sets of data in Android UI while minimizing memory usage
*   [**Gradle**](https://github.com/gradle/gradle) - Gradle is a build tool with a focus on build automation and support for multi-language development

## Authors

* [Kamil Nowak](https://github.com/nowakkamil)
* [Marcin Wrotecki](https://github.com/marcin-wrotecki)
