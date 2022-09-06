

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

![스크린샷 2022-09-06 오후 7 55 36](https://user-images.githubusercontent.com/49789441/188619946-61b14ec5-ead4-4621-b119-a4e81c10764a.png)

![스크린샷 2022-09-06 오후 7 56 27](https://user-images.githubusercontent.com/49789441/188620141-47269a99-2c4d-4c27-9e8a-0c42c522a6c6.png)

1. Run

   ![스크린샷 2022-09-06 오후 7 56 49](https://user-images.githubusercontent.com/49789441/188620301-8cb1c301-2528-4c8d-88bf-c03739efa2fc.png)

2. Archive

   ![스크린샷 2022-09-06 오후 7 57 33](https://user-images.githubusercontent.com/49789441/188620463-21992183-8d37-44fd-9903-7622cad7d950.png)



## Apply

Android Studio - Preferences - Plugin

![스크린샷 2022-09-06 오후 8 00 19](https://user-images.githubusercontent.com/49789441/188620601-73627d44-bc5f-483a-95f0-10c10d2fce3f.png)



Plugin - build - distributions - zip file

![스크린샷 2022-09-06 오후 8 00 58](https://user-images.githubusercontent.com/49789441/188620731-9945c6bd-559a-452c-b3c5-9cdb81471cdc.png)



Restart IDE

![스크린샷 2022-09-06 오후 8 02 14](https://user-images.githubusercontent.com/49789441/188620832-7b747b70-a8c0-4411-8cf2-4f103d962678.png)



New - TestAction 실행

![스크린샷 2022-09-06 오후 8 03 00](https://user-images.githubusercontent.com/49789441/188620957-b0f17b83-4731-45db-85a3-7f1c7eedf025.png)