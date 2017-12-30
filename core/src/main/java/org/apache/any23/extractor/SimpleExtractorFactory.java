/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.any23.extractor;

import org.apache.any23.mime.MIMEType;
import org.apache.any23.rdf.Prefixes;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is a simple and default-like implementation of {@link ExtractorFactory}.
 *
 * @param <T> the type of the {@link Extractor} served by this factory.
 */
public abstract class SimpleExtractorFactory<T extends Extractor<?>> implements ExtractorFactory<T> {

    private final String name;

    private final Prefixes prefixes;

    private Collection<MIMEType> supportedMIMETypes = new ArrayList<>();

    private String exampleInput;

    protected SimpleExtractorFactory(
            String name,
            Prefixes prefixes) {
        this.name = name;
        this.prefixes = prefixes;
    }

    protected SimpleExtractorFactory(
            String name,
            Prefixes prefixes,
            Collection<String> supportedMIMETypes,
            String exampleInput
    ) {
        this.name = name;
        this.prefixes = (prefixes == null) ? Prefixes.EMPTY : prefixes;
        for (String type : supportedMIMETypes) {
            this.supportedMIMETypes.add(MIMEType.parse(type));
        }
        this.exampleInput = exampleInput;
    }

    /**
     * @return the name of the {@link Extractor}
     */
    @Override
    public String getExtractorName() {
        return name;
    }

    /**
     * @return the label of the {@link Extractor}
     */
    @Override
    public String getExtractorLabel() {
        return this.getClass().getName();
    }

    /**
     * @return the handled {@link org.apache.any23.rdf.Prefixes}
     */
    @Override
    public Prefixes getPrefixes() {
        return prefixes;
    }

    /**
     * @return the supported {@link org.apache.any23.mime.MIMEType}
     */
    @Override
    public Collection<MIMEType> getSupportedMIMETypes() {
        return supportedMIMETypes;
    }

    /**
     * @return an input example
     */
    @Override
    public String getExampleInput() {
        return exampleInput;
    }

}
