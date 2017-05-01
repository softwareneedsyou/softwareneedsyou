/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.annotations;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleElementVisitor8;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * @author Blixel
 *
 */
@SupportedAnnotationTypes({"fr.esgi.projet.softwareneedsyou.api.annotations.Plugin"})
@SupportedSourceVersion(SourceVersion.RELEASE_8) //suppression warning 1.6 < 1.8
public class PluginProcessor extends AbstractProcessor {
	Messager messager;
	Filer filer;
	
	public PluginProcessor() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation.processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.messager = processingEnv.getMessager();
		this.filer = processingEnv.getFiler();
	}

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		//Messager messager = processingEnv.getMessager();
		//Filer filer = processingEnv.getFiler();
		for (TypeElement annotation : annotations)
			for (Element elem : roundEnv.getElementsAnnotatedWith(annotation)) { //Pour chaque Type/Classe
				final TypeElement type = (TypeElement) elem;
				//TODO: if(param not null)
				//TODO: if(class exist)
				messager.printMessage(Diagnostic.Kind.NOTE, type.getSimpleName());
				FileObject serv = null;
				String oldContent = "";
				try {
					serv = filer.getResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/services/" + annotation.getQualifiedName());
					try(BufferedReader reader = new BufferedReader(serv.openReader(true))) {
						oldContent += reader.lines().collect(Collectors.joining("\n"));
					}
				} catch (IOException e1) {
					messager.printMessage(Diagnostic.Kind.NOTE, e1.getLocalizedMessage(), elem);
				}
				oldContent = oldContent.trim();
				try {
					serv = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "META-INF/services/" + annotation.getQualifiedName(), (Element[])null);
					serv.openWriter().append(oldContent + (oldContent.isEmpty() ? "" : "\n")).append(type.getQualifiedName()).append('\n').close();
				} catch (IOException e1) {
					messager.printMessage(Diagnostic.Kind.ERROR, e1.getLocalizedMessage(), elem);
				}
			}
		return true;
	}

}
