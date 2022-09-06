

# Intellij Code Template

## Structure

```
src
└── main
    ├── kotlin
    │   └── com
    │       └── richone
    │           └── fluttertemplate
    │               ├── action
    │               │   ├── GenerateDialog.kt
    │               │   └── PresentationAction.kt
    │               └── generator
    │                   ├── PresentationLayerGenerator.kt
    │                   ├── PresentationLayerGeneratorFactory.kt
    │                   └── components
    │                       ├── BlocEventGenerator.kt
    │                       ├── BlocGenerator.kt
    │                       ├── BlocStateGenerator.kt
    │                       ├── BuilderGenerator.kt
    │                       ├── ModelMapperGenerator.kt
    │                       ├── WidgetGenerator.kt
    │                       └── WidgetModelGenerator.kt
    └── resources
        ├── META-INF
        │   ├── plugin.xml
        │   └── pluginIcon.svg
        └── templates
            └── presentation_layer
                ├── bloc
                │   ├── bloc.dart.template
                │   ├── bloc_event.dart.template
                │   └── bloc_state.dart.template
                ├── builder.dart.template
                ├── model_mapper.dart.template
                ├── widget.dart.template
                └── widget_model.dart.template
```



## How to use

1. templates 폴더에 template 파일 정의
2. template 파일과 1:1로 대응하는 Generator 파일 components 파일에 정의

3. PresentationLayerGenerator의 init 부분에서 탐색할 파일 경로와 확장자 설정

   ```kotlin
   init {
           templateValues = mutableMapOf(
                   TEMPLATE_PASCAL_CASE to pascalCase(),
                   TEMPLATE_SNAKE_CASE to snakeCase()
           )
           try {
               val templateFolder = /*** 템플릿 파일 폴더 이름 ***/
               val resource = "/templates/$templateFolder/$templateName.{확장자 이름}.template"
               val resourceAsStream = PresentationLayerGenerator::class.java.getResourceAsStream(resource)
               templateString = CharStreams.toString(InputStreamReader(resourceAsStream, Charsets.UTF_8))
           } catch (e: Exception) {
               throw RuntimeException(e)
           }
       }
   ```

   

4. PresentationLayerGeneratorFactory에 제너레이터 등록

```kotlin
fun getBlocGenerators(name: String): List<PresentationLayerGenerator> {
	val generator = Generator(name)
	return listOf(generator...)
}
```

5. PresentationLayerGeneratorFactory의 팩토리 메서드에 맞춰 onGenerateClicked 메서드 generate 메서드 수정



## ## Build Config

![image-20220906195554597](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906195554597.png)

![image-20220906195633086](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906195633086.png)

1. Run

   ![image-20220906195703980](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906195703980.png)

2. Archive

   ![image-20220906195746840](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906195746840.png)



## Apply

Android Studio - Preferences - Plugin

![image-20220906200023006](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906200023006.png)



Plugin - build - distributions - zip file

![image-20220906200108022](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906200108022.png)



Restart IDE

![image-20220906200230541](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906200230541.png)



New - TestAction 실행

![image-20220906200307785](/Users/canlablee/Library/Application Support/typora-user-images/image-20220906200307785.png)