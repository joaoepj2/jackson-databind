# CODE EXPLORING LOG
***
06 Dec

- Add src/main/java/com/fasterxml/jackson/Main.java as entrypoint to exlore library source code into debugger
- ObjectMapper()    
  - JacksonAnnotationIntrospector()
    - WTF?
      - private final static Class<? extends Annotation>[] ANNOTATIONS_TO_INFER_SER = Class<? extends Annotation>[]) new Class<?>[] {...}
  - BaseSettings()
- ObjectMapper.writeValueAsString()
***
- Looking for some JUnit tests
- Add src/test/java/com/fasterxml/jackson/MainTest.java
- Run a failing test