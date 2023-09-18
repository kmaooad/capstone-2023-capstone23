package edu.kmaooad.capstone23.departments.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.dal.RequestsRepository;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class ApproveRequestHandler  implements CommandHandler<ApproveJoinRequest, RequestApproved> {
    @Inject
    private DepartmentsRepository departmentsRepository;

    @Inject
    private RequestsRepository requestsRepository;

    private final String approvedStatus = "approved";

    public Result<RequestApproved> handle(ApproveJoinRequest command) {
        // TODO: check if the user is authorized to approve the request (only department admins can approve requests)

        String requestId = command.getRequestId();
        Request request = requestsRepository.findById(requestId);
        if (request == null) {
            return new Result(ErrorCode.EXCEPTION, "Request not found");
        }
        request.status = approvedStatus;
        requestsRepository.update(request);

        Department department = departmentsRepository.findById(request.departmentId);
        if (department == null) {
            return new Result(ErrorCode.EXCEPTION, "Department not found");
        }

        String userName = request.userName;
        department.members.add(userName);
        departmentsRepository.update(department);

        RequestApproved result = new RequestApproved(request.id.toString());

        return new Result(result);
    }
}
