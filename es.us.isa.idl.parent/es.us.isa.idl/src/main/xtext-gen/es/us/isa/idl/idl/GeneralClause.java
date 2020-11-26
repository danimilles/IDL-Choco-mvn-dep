/**
 * generated by Xtext 2.19.0
 */
package es.us.isa.idl.idl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link es.us.isa.idl.idl.GeneralClause#getFirstElement <em>First Element</em>}</li>
 *   <li>{@link es.us.isa.idl.idl.GeneralClause#getNot <em>Not</em>}</li>
 *   <li>{@link es.us.isa.idl.idl.GeneralClause#getOpeningParenthesis <em>Opening Parenthesis</em>}</li>
 *   <li>{@link es.us.isa.idl.idl.GeneralClause#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link es.us.isa.idl.idl.GeneralClause#getClosingParenthesis <em>Closing Parenthesis</em>}</li>
 * </ul>
 *
 * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause()
 * @model
 * @generated
 */
public interface GeneralClause extends EObject
{
  /**
   * Returns the value of the '<em><b>First Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>First Element</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>First Element</em>' containment reference.
   * @see #setFirstElement(EObject)
   * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause_FirstElement()
   * @model containment="true"
   * @generated
   */
  EObject getFirstElement();

  /**
   * Sets the value of the '{@link es.us.isa.idl.idl.GeneralClause#getFirstElement <em>First Element</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>First Element</em>' containment reference.
   * @see #getFirstElement()
   * @generated
   */
  void setFirstElement(EObject value);

  /**
   * Returns the value of the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Not</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not</em>' attribute.
   * @see #setNot(String)
   * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause_Not()
   * @model
   * @generated
   */
  String getNot();

  /**
   * Sets the value of the '{@link es.us.isa.idl.idl.GeneralClause#getNot <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Not</em>' attribute.
   * @see #getNot()
   * @generated
   */
  void setNot(String value);

  /**
   * Returns the value of the '<em><b>Opening Parenthesis</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Opening Parenthesis</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Opening Parenthesis</em>' attribute.
   * @see #setOpeningParenthesis(String)
   * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause_OpeningParenthesis()
   * @model
   * @generated
   */
  String getOpeningParenthesis();

  /**
   * Sets the value of the '{@link es.us.isa.idl.idl.GeneralClause#getOpeningParenthesis <em>Opening Parenthesis</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Opening Parenthesis</em>' attribute.
   * @see #getOpeningParenthesis()
   * @generated
   */
  void setOpeningParenthesis(String value);

  /**
   * Returns the value of the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Predicate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicate</em>' containment reference.
   * @see #setPredicate(GeneralPredicate)
   * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause_Predicate()
   * @model containment="true"
   * @generated
   */
  GeneralPredicate getPredicate();

  /**
   * Sets the value of the '{@link es.us.isa.idl.idl.GeneralClause#getPredicate <em>Predicate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Predicate</em>' containment reference.
   * @see #getPredicate()
   * @generated
   */
  void setPredicate(GeneralPredicate value);

  /**
   * Returns the value of the '<em><b>Closing Parenthesis</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Closing Parenthesis</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Closing Parenthesis</em>' attribute.
   * @see #setClosingParenthesis(String)
   * @see es.us.isa.idl.idl.IdlPackage#getGeneralClause_ClosingParenthesis()
   * @model
   * @generated
   */
  String getClosingParenthesis();

  /**
   * Sets the value of the '{@link es.us.isa.idl.idl.GeneralClause#getClosingParenthesis <em>Closing Parenthesis</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Closing Parenthesis</em>' attribute.
   * @see #getClosingParenthesis()
   * @generated
   */
  void setClosingParenthesis(String value);

} // GeneralClause
