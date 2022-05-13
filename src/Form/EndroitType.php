<?php

namespace App\Form;

use App\Entity\Endroit;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class EndroitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('type')
            ->add('description')
            ->add('longitude')
            ->add('latitude')
            ->add('numero')
            ->add('image', FileType::class, array('data_class' => null, 'required'=> false))
            ->add('region_id')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Endroit::class,
        ]);
    }
}
