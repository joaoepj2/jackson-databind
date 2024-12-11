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
### First Tests
- Looking for some JUnit tests
- Add src/test/java/com/fasterxml/jackson/MainTest.java
- Run a failing test
## 09 Dec
- Getting some library history in [Brief History of Jackson the JSON processor](https://www.cowtowncoder.com/blog/archives/08-01-2013_08-31-2013.html)
## 10 Dec
- Reading about java.io.Serializable
## 11 Dec
- ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects), or to and from a general-purpose JSON Tree Model, as well as related functionality for performing conversions. ObjectMapper class has a JsonFactory as attribute.
- JsonFactory is the main factory class of Jackson package, used to configure and construct reader (aka  JsonParser) and writer (aka JsonGenerator) instances.
                                                          
                      TreeCodec                           
                          X                               
                          X                               
                      ObjectCodec                         
                          X                               
                          X                               
       XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX     
       X                  X                         X     
  ObjectReader        ObjectMapper            ObjectWriter
                        *JsonFactory

- Now MainTest class has two methods: firstTest() and parserTest(). The two tests PASS, and there is a little comment on how to make them FAIL. You can run only these tests by using VS Code Testing extension. parserTest() instantiates a JsonParser via JsonFactory.createParser() and asserts that first token is as expected.
- Move Main.main() code to MainTest.objectMapperTest()                     