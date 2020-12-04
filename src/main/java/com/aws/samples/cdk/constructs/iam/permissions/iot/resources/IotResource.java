package com.aws.samples.cdk.constructs.iam.permissions.iot.resources;

import com.aws.samples.cdk.constructs.iam.permissions.IamResource;
import com.aws.samples.cdk.constructs.iam.permissions.SharedPermissions;
import software.amazon.awscdk.core.Fn;

import java.util.Arrays;
import java.util.List;

public interface IotResource extends IamResource {
    default String getIamString() {
        String delimiter = "";
        List<String> elements = Arrays.asList("arn:aws:iot:", getRegion(), ":", getAccountId(), ":", getResourceType(), "/", getResourceValue());

        if (SharedPermissions.isRunningInLambda()) {
            // Running inside of Lambda, use a normal string
            return String.join(delimiter, elements);
        } else {
            // Running inside of CDK, use CloudFormation join function
            return Fn.join(delimiter, elements);
        }
    }

    /**
     * This value is used in ARNs (e.g. "arn:aws:iot:REGION:ACCOUNT_ID:RESOURCE_TYPE/RESOURCE_VALUE")
     *
     * @return
     */
    String getResourceValue();

    /**
     * This value is used in ARNs (e.g. "arn:aws:iot:REGION:ACCOUNT_ID:RESOURCE_TYPE/RESOURCE_VALUE")
     *
     * @return
     */
    String getResourceType();
}
