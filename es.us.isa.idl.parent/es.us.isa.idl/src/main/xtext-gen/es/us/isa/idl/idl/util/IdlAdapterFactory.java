/**
 * generated by Xtext 2.19.0
 */
package es.us.isa.idl.idl.util;

import es.us.isa.idl.idl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see es.us.isa.idl.idl.IdlPackage
 * @generated
 */
public class IdlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static IdlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IdlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = IdlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IdlSwitch<Adapter> modelSwitch =
    new IdlSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseDependency(Dependency object)
      {
        return createDependencyAdapter();
      }
      @Override
      public Adapter caseRelationalDependency(RelationalDependency object)
      {
        return createRelationalDependencyAdapter();
      }
      @Override
      public Adapter caseArithmeticDependency(ArithmeticDependency object)
      {
        return createArithmeticDependencyAdapter();
      }
      @Override
      public Adapter caseOperation(Operation object)
      {
        return createOperationAdapter();
      }
      @Override
      public Adapter caseOperationContinuation(OperationContinuation object)
      {
        return createOperationContinuationAdapter();
      }
      @Override
      public Adapter caseConditionalDependency(ConditionalDependency object)
      {
        return createConditionalDependencyAdapter();
      }
      @Override
      public Adapter caseGeneralPredicate(GeneralPredicate object)
      {
        return createGeneralPredicateAdapter();
      }
      @Override
      public Adapter caseGeneralClause(GeneralClause object)
      {
        return createGeneralClauseAdapter();
      }
      @Override
      public Adapter caseGeneralTerm(GeneralTerm object)
      {
        return createGeneralTermAdapter();
      }
      @Override
      public Adapter caseParam(Param object)
      {
        return createParamAdapter();
      }
      @Override
      public Adapter caseParamValueRelation(ParamValueRelation object)
      {
        return createParamValueRelationAdapter();
      }
      @Override
      public Adapter caseGeneralClauseContinuation(GeneralClauseContinuation object)
      {
        return createGeneralClauseContinuationAdapter();
      }
      @Override
      public Adapter caseGeneralPredefinedDependency(GeneralPredefinedDependency object)
      {
        return createGeneralPredefinedDependencyAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.Dependency <em>Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.Dependency
   * @generated
   */
  public Adapter createDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.RelationalDependency <em>Relational Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.RelationalDependency
   * @generated
   */
  public Adapter createRelationalDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.ArithmeticDependency <em>Arithmetic Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.ArithmeticDependency
   * @generated
   */
  public Adapter createArithmeticDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.Operation
   * @generated
   */
  public Adapter createOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.OperationContinuation <em>Operation Continuation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.OperationContinuation
   * @generated
   */
  public Adapter createOperationContinuationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.ConditionalDependency <em>Conditional Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.ConditionalDependency
   * @generated
   */
  public Adapter createConditionalDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.GeneralPredicate <em>General Predicate</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.GeneralPredicate
   * @generated
   */
  public Adapter createGeneralPredicateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.GeneralClause <em>General Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.GeneralClause
   * @generated
   */
  public Adapter createGeneralClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.GeneralTerm <em>General Term</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.GeneralTerm
   * @generated
   */
  public Adapter createGeneralTermAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.Param <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.Param
   * @generated
   */
  public Adapter createParamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.ParamValueRelation <em>Param Value Relation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.ParamValueRelation
   * @generated
   */
  public Adapter createParamValueRelationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.GeneralClauseContinuation <em>General Clause Continuation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.GeneralClauseContinuation
   * @generated
   */
  public Adapter createGeneralClauseContinuationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link es.us.isa.idl.idl.GeneralPredefinedDependency <em>General Predefined Dependency</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see es.us.isa.idl.idl.GeneralPredefinedDependency
   * @generated
   */
  public Adapter createGeneralPredefinedDependencyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //IdlAdapterFactory
