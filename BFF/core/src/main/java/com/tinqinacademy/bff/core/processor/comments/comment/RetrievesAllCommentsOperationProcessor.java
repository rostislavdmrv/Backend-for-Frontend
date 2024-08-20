package com.tinqinacademy.bff.core.processor.comments.comment;
import com.tinqinacademy.bff.api.models.errors.ErrorWrapper;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentOperation;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentRequest;
import com.tinqinacademy.bff.api.operations.comments.returnsallcommentsforcertainroom.ReturnCommentResponse;
import com.tinqinacademy.bff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.bff.core.processor.base.BaseOperationProcessor;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class RetrievesAllCommentsOperationProcessor extends BaseOperationProcessor<ReturnCommentRequest,ReturnCommentResponse> implements ReturnCommentOperation {

    //  private  final CommentRepository commentRepository;

    protected RetrievesAllCommentsOperationProcessor(ConversionService conversionService, Validator validator, ErrorHandler errorHandler) {
        super(conversionService, validator, errorHandler);


    }


    @Override
    public Either<ErrorWrapper, ReturnCommentResponse> process(ReturnCommentRequest input) {
        log.info("Start retrieving all the comments for certain room");

        return Try.of(() -> {

                    ReturnCommentResponse output = ReturnCommentResponse.builder()
                            .comments(List.of())
                            .build();

                    log.info("End retrieving all the comments for certain room {}",output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

}
