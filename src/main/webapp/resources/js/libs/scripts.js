//jQuery(document).ready(function() {
$(function() {	
    
    $('.game-form form input[type="text"], .game-form form textarea').on('focus', function() {
        $('.game-form form input[type="text"], .game-form form textarea').removeClass('input-error');
    });
    
    $('#newGameForm')
    .formValidation({
        message: 'This value its not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	user_id: {
                validators: {
                    notEmpty: {
                        message: 'User id is mandatory'
                    }
                }
            },
            full_name: {
            	validators: {
            		notEmpty: {
            			message: 'Full Name is mandatory'
            		}
            	}
            },
            hostname: {
            	validators: {
            		notEmpty: {
            			message: 'Hostname is mandatory'
            		}
            	}
            },
            port: {
            	validators: {
            		notEmpty: {
            			message: 'Port is mandatory'
            		}
            	}
            }
        }
    });
    
    $('#playGameForm')
    .formValidation({
        message: 'This value its not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	shot1_x: {
                validators: {
                    notEmpty: {
                        message: 'shot1 row cannot be empty'
                    },
                    regexp: {
                        regexp: /^[0-9a-fA-F]{1}$/i,
                        message: 'shot1 row invalid value'
                    }
                }
            },
            shot1_y: {
            	validators: {
            		notEmpty: {
            			message: 'shot1 column cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot1 column invalid value'
            		}
            	}
            },
            shot2_x: {
            	validators: {
            		notEmpty: {
            			message: 'shot2 row cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot2 row invalid value'
            		}
            	}
            },
            shot2_y: {
            	validators: {
            		notEmpty: {
            			message: 'shot2 column cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot2 column invalid value'
            		}
            	}
            },
            shot3_x: {
            	validators: {
            		notEmpty: {
            			message: 'shot3 row cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot3 row invalid value'
            		}
            	}
            },
            shot3_y: {
            	validators: {
            		notEmpty: {
            			message: 'shot3 column cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot3 row invalid value'
            		}
            	}
            },
            shot4_x: {
            	validators: {
            		notEmpty: {
            			message: 'shot4 row cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot4 row invalid value'
            		}
            	}
            },
            shot4_y: {
            	validators: {
            		notEmpty: {
            			message: 'shot4 column cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot4 coloumn invalid value'
            		}
            	}
            },
            shot5_x: {
            	validators: {
            		notEmpty: {
            			message: 'shot5 row cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot5 row invalid value'
            		}
            	}
            },
            shot5_y: {
            	validators: {
            		notEmpty: {
            			message: 'shot5 column cannot be empty'
            		},
            		regexp: {
            			regexp: /^[0-9a-fA-F]{1}$/i,
            			message: 'shot5 column invalid value'
            		}
            	}
            }
        }
    });
    
});
