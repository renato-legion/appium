# new feature
# Tags: optional
@PacoTeamQA
Feature: AppiumTest


  @Regresion @Transicion3D
  Scenario: Mostrar transicion 3D
    Given usuario abre la aplicacion
    When usuario realiza operaciones
    Then cierra la aplicacion


  @Regresion @NotificacionTextoLargo
  Scenario: Mostrar notificacion con texto largo
    Given usuario abre la aplicacion
    And usuario se dirige a Notificacion con texto
    When clickea mostrar notoficacion larga
    Then la app te muestra una notificacion

  @Regresion @ReproducirAudioDesdeRecursos
  Scenario: Reproducir un audio desde los recursos del proyecto
    Given usuario abre la aplicacion
    And usuario abre MediaPLayer
    When clickea sobre audio desde recursos
    Then la app reproduce el audio