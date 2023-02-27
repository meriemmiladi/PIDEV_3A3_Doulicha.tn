/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractor;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObjectFactory;
import com.optimaize.langdetect.text.TextObject;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanguageDetection {
    
    private static final TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
    private static final LanguageDetector languageDetector;
    
    static {
        List<LanguageProfile> languageProfiles = null;
        try {
            languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        } catch (IOException ex) {
            Logger.getLogger(LanguageDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
        languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
            .withProfiles(languageProfiles)
            .build();
    }
    
    public static String detectLanguage(String text) {
        TextObject textObject = textObjectFactory.forText(text);
        com.google.common.base.Optional<LdLocale> lang = languageDetector.detect(textObject);
        return lang.isPresent() ? lang.get().getLanguage() : "";
    }
}