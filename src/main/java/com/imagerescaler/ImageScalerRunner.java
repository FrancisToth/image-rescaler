package com.imagerescaler;

import com.common.Closure;
import com.imagerescaler.file.FileSet;
import com.imagerescaler.writer.FileWriterDecorator;
import com.imagerescaler.xml.XMLFile;
import com.imagerescaler.xml.XMLFileParser;
import com.imagerescaler.xml.XMLNode;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.common.util.RunnerUtil.resolveMandatoryParameter;
import static com.common.util.RunnerUtil.resolveParameter;

public class ImageScalerRunner {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        final String originFolderPath = resolveMandatoryParameter(args, 0);
        final String destinationFolderPath = resolveMandatoryParameter(args, 1);
        final long timeStamp = resolveParameter(args, 2, 0);
        final String xmlFile = resolveMandatoryParameter(args, 3);
        final String xsdFile = resolveParameter(args, 4, "image.scaler.xsd");

        final FileWriterDecorator decorator = new FileWriterDecorator(destinationFolderPath);
        XMLFile xmlfile = new XMLFileParser().parseXMLFile(xmlFile, xsdFile);
        xmlfile.each("fileset", new Closure<XMLNode>() {
            @Override
            public void execute(XMLNode node) {
                FileSet filteredFileSet = new FileSet(originFolderPath) //
                        .include(node.getAttributeValue("include")) //
                        .exclude(node.getAttributeValue("exclude")) //
                        .modifiedSince(timeStamp);

                filteredFileSet.write(decorator.parse(node));
            }
        });
    }
}