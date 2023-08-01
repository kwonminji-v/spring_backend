package com.jpabook.jpashop.api;


import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /** 회원 목록 조회 관련 API 시작 */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findmembers = memberService.findMembers();
        List<MemberDto> collect = findmembers.stream().map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T date;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    //만약 회원에 대한 정보만 불러오길 바랬는데 Member Entity에는 주문 정보도 있기때문에 주문 정보도 같이 조회되며 외부에 노출됨
    //Member 엔티티에서 orders에 @JsonIgnore을 작성해 해당 데이터는 제외되도록 설정

    /** 회원 목록 조회 관련 API 끝 */
    
    /** 회원 등록 관련 API 시작 */
    /**
     * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
     * 문제점
     * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * - 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
     * - 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위
     한 모든 요청 요구사항을 담기는 어렵다.
     * - 엔티티가 변경되면 API 스펙이 변한다.
     * 결론
     * - API 요청 스펙에 맞추어 별도의 DTO를 파라미터로 받는다.
     */
    @PostMapping("/api/v1/members") //회원을 등록하는 api
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        //파라미터를 받는데 @Valid는 속성, 매개변수, 반환 유형을 검증할 때 사용
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    @PostMapping("/api/v2/members")
    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     */
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }
    @Data
    static class CreateMemberResponse {
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }

    }
    /** 회원 등록 관련 API 끝 */

    /** 회원 정보 수정(update-변경) API 시작 */
    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());

    }
    @Data
    static class UpdateMemberRequest {

        private String name;

    }
    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;

    }
    /** 회원 정보 수정(update-변경) API 끝 */


}

/**
 * 해당 코드 필기
 * @RequestBody와 @Valid 어노테이션
 * @Valid 어노테이션은 Spring Framework에서 제공하는 유효성 검증 기능을 활용합니다.
 * 이 어노테이션은 주로 데이터를 입력받는 객체(또는 해당 객체의 속성)가 유효한 값인지 확인하고자 할 때 사용됩니다.
 * 유효성 검증은 해당 객체가 정상적으로 생성되고 데이터가 유효한지를 확인하는데 도움을 줍니다.
 *
 * 위의 코드에서 @RequestBody 어노테이션은 HTTP 요청의 본문(body)에 있는 JSON 데이터를 Member 객체로 매핑합니다.
 * 이후 @Valid 어노테이션을 사용하여 Member 객체가 유효한 값들로 채워져 있는지 확인합니다.
 * 예를 들어, Member 객체의 필드들 중에 양수만 입력 가능한 필드가 있다면, 해당 필드에 음수 값을 입력했을 때 유효성 검증에서 실패하게 됩니다.
 *
 * @Valid 어노테이션은 Java Bean Validation API (javax.validation)의 일부입니다.
 * 이를 사용하여 객체의 속성들에 대한 유효성 검증 규칙을 정의할 수 있습니다.
 * @NotNull, @Size, @Min, @Max, @Pattern 등의 어노테이션을 사용하여 각 속성에 대한 유효성 검증을 설정할 수 있습니다.
 *
 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ@POSTMAPPINGㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
 * 첫 번째 코드 (@PostMapping("/api/v1/members")):
 * 이 코드에서 @RequestBody @Valid Member member는 HTTP 요청의 본문(body)에 있는 JSON 데이터를 Member 객체로 매핑하고,
 * 이 객체의 유효성을 검사합니다. 즉, 클라이언트가 JSON 형식으로 전송한 데이터를 Member 객체로 변환하고,
 * 이 객체의 필드들에 대해 정의된 유효성 검증을 수행합니다.

 * Member 객체는 이미 데이터베이스와 매핑되는 엔티티로 정의되어 있으며,
 * 클라이언트가 제공한 데이터를 이 객체로 직접 매핑하여 사용합니다.
 *
 * 두 번째 코드 (@PostMapping("/api/v2/members")):
 * 이 코드에서 @RequestBody @Valid CreateMemberRequest request는 HTTP 요청의 본문(body)에 있는 JSON 데이터를 CreateMemberRequest 객체로 매핑하고,
 * 이 객체의 유효성을 검사합니다. CreateMemberRequest는 Member 엔티티와는 별도의 데이터 전송 객체(Data Transfer Object, DTO)로 정의되어 있습니다.
 *
 * CreateMemberRequest 객체는 Member 엔티티와 1:1로 매핑되지 않고, 필요한 데이터만 담아서 전송하기 위해 사용되는 객체입니다.
 * 클라이언트가 전송한 데이터를 먼저 CreateMemberRequest 객체로 매핑하고, 이 객체에서 필요한 데이터를 추출하여 Member 엔티티를 생성하고 사용합니다.
 *
 * 즉, 두 코드의 차이점은 클라이언트가 전송하는 데이터의 형식과 서버에서 사용하는 엔티티의 형식 사이의 매핑 방식이 다르다는 것입니다.
 * 두 번째 코드에서는 별도의 데이터 전송 객체인 CreateMemberRequest를 사용하여 필요한 데이터만 추출하여 Member 엔티티를 생성합니다.
 * 이를 통해 데이터 전송과 엔티티 매핑 간의 불필요한 의존성을 최소화하고, 코드의 유연성과 확장성을 높일 수 있습니다.
 * */