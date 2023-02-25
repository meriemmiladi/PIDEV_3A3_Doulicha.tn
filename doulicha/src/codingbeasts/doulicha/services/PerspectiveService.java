package codingbeasts.doulicha.services;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.commentanalyzer.v1alpha1.CommentAnalyzer;
import com.google.api.services.commentanalyzer.v1alpha1.model.AnalyzeCommentRequest;
import com.google.api.services.commentanalyzer.v1alpha1.model.AnalyzeCommentResponse;
import com.google.api.services.commentanalyzer.v1alpha1.model.AttributeParameters;
import com.google.api.services.commentanalyzer.v1alpha1.model.Context;
import com.google.api.services.commentanalyzer.v1alpha1.model.TextEntry;
import java.util.HashMap;
import java.util.Map;

public class PerspectiveService {
    private static final String API_KEY = "AIzaSyB3KH8n7rtlgWsk3bCT6gzS1UgWOoXBvX8";
    
    private static final CommentAnalyzer.Builder BUILDER;
    
    static {
        try {
            BUILDER = new CommentAnalyzer.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                null
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AnalyzeCommentResponse analyzeComment(String comment) throws IOException {
    CommentAnalyzer commentanalyzer = BUILDER.build();
    AnalyzeCommentRequest request = new AnalyzeCommentRequest();
    request.setComment(new TextEntry().setText(comment));
    Context context = new Context();
    context.setEntries(Collections.singletonList(new TextEntry().setText(comment)));
    request.setContext(context);
    Map<String, AttributeParameters> requestedAttributes = new HashMap<>();
    requestedAttributes.put("TOXICITY", new AttributeParameters());
    request.setRequestedAttributes(requestedAttributes);
    CommentAnalyzer.Comments.Analyze analyze = commentanalyzer.comments().analyze(request);
    analyze.setKey(API_KEY);
    return analyze.execute();
}

}
