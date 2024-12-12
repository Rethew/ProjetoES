package pt.estgp.es.projetoes.spring.services.security.exemplosargs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static org.burningwave.core.assembler.StaticComponentContainer.ByFieldOrByMethodPropertyAccessor;


/**
 *
 * Several ways of obtain arguments from intercepted method
 *
 *
 *
 */
@Aspect
@Component
public class CustomArgsSecurityAspect {

    @Pointcut("@annotation(pt.estgp.es.projetoes.spring.services.security.exemplosargs.AnotacaoSimplesParaObterOsArgsDiretamente) " +
            " && args(ucId,..,alunoId)")
    private void ucIdalunoId(Integer alunoId, Integer ucId){}

    @Around("ucIdalunoId(alunoId,ucId)")
    public Object obterIdsDiretamenteDoServico(ProceedingJoinPoint pjp, Integer alunoId, Integer ucId) throws Throwable {


        System.out.println("AnotacaoSimplesParaObterOsArgsDiretamente: idAluno = " + alunoId);
        System.out.println("AnotacaoSimplesParaObterOsArgsDiretamente : idUc = " + ucId);

        return pjp.proceed();
    }


    /*
    //OU APENAS o Around sem point cut
    @Around("@annotation(pt.estgp.es.spring.services.security.exemplosargs.AnotacaoSimplesParaObterOsArgsDiretamente) " +
            " && args(ucId,..,alunoId)")
    public Object obterIdsDiretamenteDoServico(ProceedingJoinPoint pjp, Integer alunoId, Integer ucId) throws Throwable {

        System.out.println("AnotacaoSimplesParaObterOsArgsDiretamente: idAluno = " + alunoId);
        System.out.println("AnotacaoSimplesParaObterOsArgsDiretamente : idUc = " + ucId);

        return pjp.proceed();
    }
    */







    @Around("@annotation(anotacaoComArgsUcAluno)")
    public Object obterIdsDosArgs(ProceedingJoinPoint pjp, AnotacaoComArgsUcAluno anotacaoComArgsUcAluno) throws Throwable {


        Integer idAluno = (Integer) pjp.getArgs()[anotacaoComArgsUcAluno.parametroComIdAluno()];
        Integer idUc = (Integer) pjp.getArgs()[anotacaoComArgsUcAluno.parametroComIdUc()];

        System.out.println("AnotacaoComArgsUcAluno: idAluno: " + idAluno);
        System.out.println("AnotacaoComArgsUcAluno: idUc: " + idUc);

        return pjp.proceed();
    }








    @Around("@annotation(anotacaoComArgsUcAluno)")
    public Object obterIdsDosObjectsComPaths(ProceedingJoinPoint pjp, AnotacaoComBeanPathParaArgsUcAluno anotacaoComArgsUcAluno) throws Throwable {

        Object startObjectForAluno = pjp.getArgs()[anotacaoComArgsUcAluno.argAluno()];
        Object startObjectForUc = pjp.getArgs()[anotacaoComArgsUcAluno.argUc()];


        System.out.println("AnotacaoComBeanPathParaArgsUcAluno:");
        System.out.println("startObjectForAluno" + startObjectForAluno);
        System.out.println("startObjectForUc" + startObjectForUc);
        System.out.println("anotacaoComArgsUcAluno.pathParaParametroComIdAluno() = " + anotacaoComArgsUcAluno.pathParaParametroComIdAluno());
        System.out.println("anotacaoComArgsUcAluno.pathParaParametroComIdUc() = " + anotacaoComArgsUcAluno.pathParaParametroComIdUc());


        /**
         * Must include this dependency in pom.xml
         *
         *         <dependency>
         *             <groupId>org.burningwave</groupId>
         *             <artifactId>core</artifactId>
         *             <version>12.65.2</version>
         *         </dependency>
         *
         *         tutorial
         *         https://www.burningwave.org/how-getting-a-property-of-a-java-bean-through-path/
         */
        Integer idAluno = ByFieldOrByMethodPropertyAccessor.get(startObjectForAluno, anotacaoComArgsUcAluno.pathParaParametroComIdAluno());
        Integer idUc = ByFieldOrByMethodPropertyAccessor.get(startObjectForUc, anotacaoComArgsUcAluno.pathParaParametroComIdUc());

        System.out.println("idAluno = " + idAluno);
        System.out.println("idUc = " + idUc);

        return pjp.proceed();
    }




    @Around("@annotation(anotacaoComArgsUcAluno)")
    public Object obterIdsDosObjectsComPathsMaisComplexo(ProceedingJoinPoint pjp, AnotacaoComBeanPathParaArgsUcAlunoMaisComplexo anotacaoComArgsUcAluno) throws Throwable {

        System.out.println();
        String alunoPath = anotacaoComArgsUcAluno.pathParaParametroComIdAluno();
        String ucPath = anotacaoComArgsUcAluno.pathParaParametroComIdUc();

        //"0.aluno.id"
        int endIndexObjectAluno = alunoPath.indexOf("."); //1
        String number = alunoPath.substring(0,endIndexObjectAluno);
        System.out.println("Using arg:" + number + " for aluno");
        int argNumberAluno = Integer.parseInt(number);

        int endIndexObjectUc = ucPath.indexOf(".");
        number = ucPath.substring(0,endIndexObjectUc);
        System.out.println("Using arg:" + number + " for uc");
        int argNumberUc = Integer.parseInt(number);

        Object startObjectForAluno = pjp.getArgs()[argNumberAluno];
        Object startObjectForUc = pjp.getArgs()[argNumberUc];

        System.out.println("startObjectForAluno" + startObjectForAluno);
        System.out.println("startObjectForUc" + startObjectForUc);

        //"aluno.id"
        String remainPathAluno = anotacaoComArgsUcAluno.pathParaParametroComIdAluno().substring(endIndexObjectAluno+1);
        System.out.println("remainPathAluno = " + remainPathAluno);
        //"id"
        String remainPathUc = anotacaoComArgsUcAluno.pathParaParametroComIdUc().substring(endIndexObjectUc+1);
        System.out.println("remainPathUc = " + remainPathUc);
        /**
         * Must include this dependency in pom.xml
         *
         *         <dependency>
         *             <groupId>org.burningwave</groupId>
         *             <artifactId>core</artifactId>
         *             <version>12.65.2</version>
         *         </dependency>
         */
        Integer idAluno = ByFieldOrByMethodPropertyAccessor.get(startObjectForAluno, remainPathAluno);
        Integer idUc = ByFieldOrByMethodPropertyAccessor.get(startObjectForUc, remainPathUc);

        System.out.println("idAluno = " + idAluno);
        System.out.println("idUc = " + idUc);

        return pjp.proceed();
    }







}