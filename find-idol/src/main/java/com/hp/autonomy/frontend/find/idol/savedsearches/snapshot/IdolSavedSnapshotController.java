package com.hp.autonomy.frontend.find.idol.savedsearches.snapshot;

import com.autonomy.aci.client.services.AciErrorException;
import com.hp.autonomy.frontend.find.core.savedsearches.EmbeddableIndex;
import com.hp.autonomy.frontend.find.core.savedsearches.snapshot.SavedSnapshot;
import com.hp.autonomy.frontend.find.core.savedsearches.snapshot.SavedSnapshotController;
import com.hp.autonomy.frontend.find.core.savedsearches.snapshot.SavedSnapshotService;
import com.hp.autonomy.searchcomponents.core.search.DocumentsService;
import com.hp.autonomy.searchcomponents.core.search.SearchResult;
import com.hp.autonomy.searchcomponents.idol.search.IdolQueryRestrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(SavedSnapshotController.PATH)
public class IdolSavedSnapshotController extends SavedSnapshotController<String, SearchResult, AciErrorException> {

    @Autowired
    public IdolSavedSnapshotController(SavedSnapshotService service, DocumentsService<String, SearchResult, AciErrorException> documentsService) {
        super(service, documentsService);
    }

    private List<String> getDatabases(Set<EmbeddableIndex> indexes) {
        List<String> databases = new ArrayList<>();

        for(EmbeddableIndex index: indexes) {
            databases.add(index.getName());
        }

        return databases;
    }

    @Override
    protected String getStateToken(final SavedSnapshot snapshot) throws AciErrorException {
        IdolQueryRestrictions.Builder queryRestrictionsBuilder = new IdolQueryRestrictions.Builder()
                .setDatabases(this.getDatabases(snapshot.getIndexes()))
                .setQueryText(this.getQueryText(snapshot)).setFieldText(this.getFieldText(snapshot.getParametricValues()))
                .setMaxDate(snapshot.getMaxDate())
                .setMinDate(snapshot.getMinDate());

        return documentsService.getStateToken(queryRestrictionsBuilder.build(), Integer.MAX_VALUE);
    }
}