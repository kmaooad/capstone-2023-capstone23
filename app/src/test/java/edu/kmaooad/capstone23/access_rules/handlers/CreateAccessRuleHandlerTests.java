package edu.kmaooad.capstone23.access_rules.handlers;

import edu.kmaooad.capstone23.access_rules.commands.CreateAccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRule;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleFromEntityType;
import edu.kmaooad.capstone23.access_rules.dal.AccessRuleToEntityType;
import edu.kmaooad.capstone23.access_rules.events.AccessRuleCreated;
import edu.kmaooad.capstone23.activities.commands.CreateCourse;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.events.CourseCreated;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.group_templates.commands.CreateGroupTemplate;
import edu.kmaooad.capstone23.group_templates.events.GroupTemplateCreated;
import edu.kmaooad.capstone23.groups.commands.CreateGroup;
import edu.kmaooad.capstone23.groups.events.GroupCreated;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;

@QuarkusTest
public class CreateAccessRuleHandlerTests {

    @Inject
    CommandHandler<CreateOrg, OrgCreated> createOrgHandler;

    @Inject
    CommandHandler<CreateDepartment, DepartmentCreated> createDepartmentHandler;

    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createMemberHandler;

    @Inject
    CommandHandler<CreateGroupTemplate, GroupTemplateCreated> templateHandler;

    @Inject
    CommandHandler<CreateGroup, GroupCreated> groupHandler;

    @Inject
    CommandHandler<CreateCourse, CourseCreated> courseHandler;

    @Inject
    CommandHandler<CreateAccessRule, AccessRuleCreated> ruleHandler;

    private String member;
    private String department;
    private String org;
    private String group;
    private String course;
    @BeforeEach
    private void prepare(){
        member = createMember();
        department = createDepartment();
        org = createOrg();
        group = createGroup();
        course = createCourse();
    }

    @Test
    @DisplayName("Create Access Rule: member to department")
    public void createRuleMemberToDepartment() {
        Result<AccessRuleCreated> result = addAccessRule(member, AccessRuleFromEntityType.Member, department, AccessRuleToEntityType.Department);
        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Create Access Rule: member to group")
    public void createRuleMemberToGroup() {
        Result<AccessRuleCreated> result = addAccessRule(member, AccessRuleFromEntityType.Member, group, AccessRuleToEntityType.Group);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: member to course")
    public void createRuleMemberToCourse() {
        Result<AccessRuleCreated> result = addAccessRule(member, AccessRuleFromEntityType.Member, course, AccessRuleToEntityType.Course);
        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Create Access Rule: member to organisation")
    public void createRuleMemberToOrganisation() {
        Result<AccessRuleCreated> result = addAccessRule(member, AccessRuleFromEntityType.Member, org, AccessRuleToEntityType.Organisation);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: to non existent")
    public void createRuleMemberToNonExistent() {
        Result<AccessRuleCreated> result = addAccessRule(member, AccessRuleFromEntityType.Member, new ObjectId().toString(), AccessRuleToEntityType.Organisation);
        Assertions.assertFalse(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: from non existent")
    public void createRuleMemberFromNonExistent() {
        Result<AccessRuleCreated> result = addAccessRule(new ObjectId().toString(), AccessRuleFromEntityType.Member, new ObjectId().toString(), AccessRuleToEntityType.Organisation);
        Assertions.assertFalse(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: department to organisation")
    public void createRuleDepartmentToOrganisation() {
        Result<AccessRuleCreated> result = addAccessRule(department, AccessRuleFromEntityType.Department, org, AccessRuleToEntityType.Organisation);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: department to department")
    public void createRuleDepartmentToDepartment() {
        Result<AccessRuleCreated> result = addAccessRule(department, AccessRuleFromEntityType.Department, createDepartment(), AccessRuleToEntityType.Department);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: department to group")
    public void createRuleDepartmentToGroup() {
        Result<AccessRuleCreated> result = addAccessRule(department, AccessRuleFromEntityType.Department, group, AccessRuleToEntityType.Group);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: department to course")
    public void createRuleDepartmentToCourse() {
        Result<AccessRuleCreated> result = addAccessRule(department, AccessRuleFromEntityType.Department, course, AccessRuleToEntityType.Course);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: organisation to course")
    public void createRuleOrganisationToCourse() {
        Result<AccessRuleCreated> result = addAccessRule(org, AccessRuleFromEntityType.Organisation, course, AccessRuleToEntityType.Course);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: organisation to organisation")
    public void createRuleOrganisationToOrganisation() {
        Result<AccessRuleCreated> result = addAccessRule(org, AccessRuleFromEntityType.Organisation, createOrg(), AccessRuleToEntityType.Organisation);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: organisation to group")
    public void createRuleOrganisationToGroup() {
        Result<AccessRuleCreated> result = addAccessRule(org, AccessRuleFromEntityType.Organisation, group, AccessRuleToEntityType.Group);
        Assertions.assertTrue(result.isSuccess());
    }
    @Test
    @DisplayName("Create Access Rule: organisation to department")
    public void createRuleOrganisationToDepartment() {
        Result<AccessRuleCreated> result = addAccessRule(org, AccessRuleFromEntityType.Organisation, department, AccessRuleToEntityType.Department);
        Assertions.assertTrue(result.isSuccess());
    }
    private Result<AccessRuleCreated> addAccessRule(String fromId, AccessRuleFromEntityType fromType, String toId, AccessRuleToEntityType toType){
        CreateAccessRule command = new CreateAccessRule();
        command.setFromEntityId(new ObjectId(fromId));
        command.setFromEntityType(fromType.toString());
        command.setToEntityId(new ObjectId(toId));
        command.setToEntityType(toType.toString());
        command.setRuleType("Allow");

        Result<AccessRuleCreated> result = ruleHandler.handle(command);

        return result;
    }

    private String createMember(){
        CreateBasicMember command = new CreateBasicMember();
        command.setOrgId(new ObjectId(createOrg()));
        command.setFirstName("John");
        command.setLastName("Doe");
        String id = new ObjectId().toString();
        command.setEmail(id.substring(id.length()-5) +"@gmail.com");

        Result<BasicMemberCreated> result = createMemberHandler.handle(command);

        return result.getValue().getMemberId();
    }

    private String createDepartment(){
        createOrg();
        CreateDepartment command = new CreateDepartment();
        command.setParent("NaUKMA");
        command.setName("department");
        command.setDescription("description");

        Result<DepartmentCreated> result = createDepartmentHandler.handle(command);

        return result.getValue().getId();
    }

    private String createOrg(){
        CreateOrg command = new CreateOrg();
        command.setOrgName("NaUKMA");
        command.industry = "Education";
        command.website = "https://www.ukma.edu.ua/eng/";
        Result<OrgCreated> result = createOrgHandler.handle(command);
        return result.getValue().getOrgId();
    }

    private String createGroup(){
        CreateGroupTemplate templateCommand = new CreateGroupTemplate();
        templateCommand.setGroupTemplateName("template");
        Result<GroupTemplateCreated> resultForTemplate = templateHandler.handle(templateCommand);

        CreateGroup groupCommand = new CreateGroup();
        groupCommand.setGroupName("group");
        groupCommand.setTemplateId(resultForTemplate.getValue().getGroupTemplateId().toString());
        Result<GroupCreated> resultForGroup = groupHandler.handle(groupCommand);

        return resultForGroup.getValue().getGroupId();
    }
    private String createCourse(){
        CreateCourse createCourse = new CreateCourse();
        createCourse.setName("course");
        Result<CourseCreated> result = courseHandler.handle(createCourse);
        return result.getValue().getId();
    }

}
