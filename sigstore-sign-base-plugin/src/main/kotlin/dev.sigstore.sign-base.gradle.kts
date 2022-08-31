/*
 * Copyright 2022 The Sigstore Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
import dev.sigstore.sign.SigstoreSignExtension

// https://github.com/gradle/gradle/pull/16627
inline fun <reified T: Named> AttributeContainer.attribute(attr: Attribute<T>, value: String) =
    attribute(attr, objects.named<T>(value))

val sigstoreClient by configurations.creating {
    description = "Declares sigstore client dependencies"
    isCanBeResolved = false
    isCanBeConsumed = false
    defaultDependencies {
        // TODO: un-comment one sigstore-java is released
        // add(project.dependencies.create("dev.sigstore:sigstore-java:1.0"))
    }
}

val sigstoreClientClasspath by configurations.creating {
    description = "Resolves Sigstore dependencies"
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(sigstoreClient)
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, Category.LIBRARY)
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, LibraryElements.JAR)
        attribute(Usage.USAGE_ATTRIBUTE, Usage.JAVA_RUNTIME)
        attribute(Bundling.BUNDLING_ATTRIBUTE, Bundling.EXTERNAL)
    }
}

val sigstoreSign = extensions.create("sigstoreSign", SigstoreSignExtension::class, project)
