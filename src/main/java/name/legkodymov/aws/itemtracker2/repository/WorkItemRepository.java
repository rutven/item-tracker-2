package name.legkodymov.aws.itemtracker2.repository;

import name.legkodymov.aws.itemtracker2.model.WorkItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
}
