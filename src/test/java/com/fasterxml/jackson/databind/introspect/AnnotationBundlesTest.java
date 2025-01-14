package com.fasterxml.jackson.databind.introspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.testutil.DatabindTestUtil;

import static org.junit.jupiter.api.Assertions.*;

// Tests mostly for ability to create "annotation bundles"
public class AnnotationBundlesTest extends DatabindTestUtil
{
    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonIgnore
    private @interface MyIgnoral { }

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonProperty("foobar")
    private @interface MyRename { }

    protected final static class Bean {
        @MyIgnoral
        public String getIgnored() { return "foo"; }

        @MyRename
        public int renamed = 13;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JsonAutoDetect(fieldVisibility=Visibility.NONE,
            getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE)
    @JacksonAnnotationsInside
    public @interface JsonAutoDetectOff {}

    @JsonAutoDetectOff
    public class NoAutoDetect {
      public int getA() { return 13; }

      @JsonProperty
      public int getB() { return 5; }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonProperty("_id")
    public @interface Bundle92 {}

    public class Bean92 {
        @Bundle92
        protected String id = "abc";
    }

    @HolderB
    @JacksonAnnotationsInside
    @Retention(RetentionPolicy.RUNTIME)
    static @interface HolderA {}

    @HolderA
    @JacksonAnnotationsInside
    @Retention(RetentionPolicy.RUNTIME)
    static @interface HolderB {}

    static class RecursiveHolder {
        @HolderA public int unimportant = 42;
    }

    static class RecursiveHolder2 {
        @HolderA public int getValue() { return 28; }
    }

    static class RecursiveHolder3 {
        public int x;

        @JsonCreator
        @HolderA
        public RecursiveHolder3(int x) { this.x = x; }
    }

    @JsonProperty
    @JacksonAnnotationsInside
    @Retention(RetentionPolicy.RUNTIME)
    static @interface InformativeHolder {
        // doesn't really contribute to the test, but would be impossible without this feature
        boolean important() default true;
    }

    static class InformingHolder {
        @InformativeHolder public int unimportant = 42;
    }

    @SuppressWarnings("serial")
    static class BundleAnnotationIntrospector extends JacksonAnnotationIntrospector {
        @Override
        public PropertyName findNameForSerialization(Annotated a)
        {
            InformativeHolder informativeHolder = a.getAnnotation(InformativeHolder.class);
            if ((informativeHolder != null) && informativeHolder.important()) {
                return PropertyName.construct("important");
            }
            return super.findNameForSerialization(a);
        }
    }

    /*
    /**********************************************************
    /* Test methods
    /**********************************************************
     */

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testKeepAnnotationBundle() throws Exception
    {
        MAPPER.setAnnotationIntrospector(new BundleAnnotationIntrospector());
        assertEquals("{\"important\":42}", MAPPER.writeValueAsString(new InformingHolder()));
    }

    @Test
    public void testRecursiveBundlesField() throws Exception {
        assertEquals("{\"unimportant\":42}", MAPPER.writeValueAsString(new RecursiveHolder()));
    }

    @Test
    public void testRecursiveBundlesMethod() throws Exception {
        assertEquals("{\"value\":28}", MAPPER.writeValueAsString(new RecursiveHolder2()));
    }

    @Test
    public void testRecursiveBundlesConstructor() throws Exception {
        RecursiveHolder3 result = MAPPER.readValue("17", RecursiveHolder3.class);
        assertNotNull(result);
        assertEquals(17, result.x);
    }

    @Test
    public void testBundledIgnore() throws Exception
    {
        assertEquals("{\"foobar\":13}", MAPPER.writeValueAsString(new Bean()));
    }

    @Test
    public void testVisibilityBundle() throws Exception
    {
        assertEquals("{\"b\":5}", MAPPER.writeValueAsString(new NoAutoDetect()));
    }

    @Test
    public void testIssue92() throws Exception
    {
        assertEquals("{\"_id\":\"abc\"}", MAPPER.writeValueAsString(new Bean92()));
    }
}
