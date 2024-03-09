@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.weightcontrol.android.library)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.br.weightcontrol.core.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:util"))
    implementation(libs.androidx.dataStore.core)
    implementation(libs.koin.android)
    implementation(libs.protobuf.kotlin.lite)
}