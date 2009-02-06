package org.deri.any23.extractor.rdfa;

import java.io.InputStream;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

/**
 * An XSLT stylesheet loaded from an InputStream, can be applied
 * to DOM trees and writes the result to a {@link Writer}. 
 * 
 * TODO: XSLTStylesheet should have better error handling
 * 
 * @author Gabriele Renzi
 * @author Richard Cyganiak (richard@cyganiak.de)
 */
public class XSLTStylesheet {
	private final Transformer transformer;

	public XSLTStylesheet(InputStream xsltFile) {
		try {
			StreamSource xsltStream = new StreamSource(xsltFile);
			transformer = TransformerFactory.newInstance().newTransformer(xsltStream);
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		} catch (TransformerFactoryConfigurationError e) {
			throw new RuntimeException(e);
		}
	}

	public void applyTo(Document document, Writer output) {
		try {
			transformer.transform(new DOMSource(document), new StreamResult(output));
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}
}