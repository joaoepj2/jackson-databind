# CODE EXPLORING LOG
***
## 06 Dec
### Getting started


- Add src/main/java/com/fasterxml/jackson/Main.java as entrypoint to exlore library source code into debugger
- ObjectMapper()    
  - JacksonAnnotationIntrospector()
    - WTF?
      - private final static Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = Class<? extends Annotation>[]) new Class<?>[] {...}
  - BaseSettings()
- ObjectMapper.writeValueAsString()
***
### First Test
- Looking for some JUnit tests
- Add src/test/java/com/fasterxml/jackson/MainTest.java
- Run a failing test
## 09 Dec
- Getting some library history in [Brief History of Jackson the JSON processor](https://www.cowtowncoder.com/blog/archives/08-01-2013_08-31-2013.html)
## 10 Dec
- Reading about java.io.Serializable